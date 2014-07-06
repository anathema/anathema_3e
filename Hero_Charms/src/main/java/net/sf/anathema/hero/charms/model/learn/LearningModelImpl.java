package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmAttributeList;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.model.learn.prerequisites.CharmsToForget;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static net.sf.anathema.hero.charms.model.learn.prerequisites.CollectPrerequisiteCharms.collectPrerequisiteCharms;

public class LearningModelImpl implements LearningModel {

  private final Set<Charm> charmsLearnedOnCreation = new HashSet<>();
  private final Set<Charm> charmsLearnedWithExperience = new HashSet<>();
  private final Announcer<ICharmLearnListener> control = Announcer.to(ICharmLearnListener.class);
  private final IExtendedCharmLearnableArbitrator learnArbitrator;
  private final ICharmLearnStrategy learnStrategy;

  public LearningModelImpl(ICharmLearnStrategy learnStrategy, IExtendedCharmLearnableArbitrator arbitrator) {
    this.learnStrategy = learnStrategy;
    this.learnArbitrator = arbitrator;
  }

  @Override
  public void toggleLearned(Charm charm) {
    learnStrategy.toggleLearned(this, charm);
  }

  @Override
  public void toggleLearnedOnCreation(Charm charm) {
    if (charmsLearnedOnCreation.contains(charm)) {
      forgetCharm(charm, false);
      return;
    }
    learnCharm(charm, false);
  }

  @Override
  public void toggleExperienceLearnedCharm(Charm charm) {
    if (charmsLearnedOnCreation.contains(charm)) {
      fireNotForgettableEvent(charm);
      return;
    }
    if (charmsLearnedWithExperience.contains(charm)) {
      forgetCharm(charm, true);
      return;
    }
    if (!learnArbitrator.isLearnable(charm)) {
      fireNotLearnableEvent(charm);
      return;
    }
    learnCharm(charm, true);
  }

  @Override
  public void forgetCharm(Charm charm, boolean experienced) {
    if (isForgettable(charm)) {
      if (experienced) {
        charmsLearnedWithExperience.remove(charm);
      } else {
        charmsLearnedOnCreation.remove(charm);
      }
      fireCharmForgotten(charm);
      forgetChildren(charm, experienced);
    }
  }

  @Override
  public void learnCharm(Charm charm, boolean experienced) {
    learnParents(charm, experienced);
    learnCharmNoParents(charm, experienced, true);
  }

  @Override
  public void learnCharmNoParents(Charm charm, boolean experienced, boolean announce) {
    if (experienced) {
      charmsLearnedWithExperience.add(charm);
    } else {
      charmsLearnedOnCreation.add(charm);
    }
    if (announce) {
      fireCharmLearned(charm);
    }
  }

  private void forgetChildren(Charm charm, boolean experienced) {
    for (Charm child : getLearnFollowUpCharms(charm, learnArbitrator)) {
      forgetCharm(child, experienced);
    }
  }

  private Set<Charm> getLearnFollowUpCharms(Charm charm, CharmLearnArbitrator learnArbitrator) {
    CharmsToForget charmsToForget = new CharmsToForget(charm, learnArbitrator);
    return charmsToForget.getLearnFollowUpCharms();
  }

  private void learnParents(Charm charm, boolean experienced) {
    for (Charm parent : collectPrerequisiteCharms(charm, learnArbitrator)) {
      if (!isLearned(parent)) {
        learnCharm(parent, experienced);
      }
    }
  }

  private void fireCharmLearned(Charm charm) {
    control.announce().charmLearned(charm);
  }

  private void fireCharmForgotten(Charm charm) {
    control.announce().charmForgotten(charm);
  }

  private void fireNotLearnableEvent(Charm charm) {
    control.announce().charmNotLearnable(charm);
  }

  private void fireNotForgettableEvent(Charm charm) {
    control.announce().charmNotUnlearnable(charm);
  }

  @Override
  public void fireRecalculateRequested() {
    control.announce().recalculateRequested();
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    control.addListener(listener);
  }

  @Override
  public Charm[] getCreationLearnedCharms() {
    return charmsLearnedOnCreation.toArray(new Charm[charmsLearnedOnCreation.size()]);
  }

  @Override
  public Charm[] getExperienceLearnedCharms() {
    return charmsLearnedWithExperience.toArray(new Charm[charmsLearnedWithExperience.size()]);
  }

  @Override
  public boolean isLearned(Charm charm) {
    return learnStrategy.isLearned(this, charm);
  }

  /**
   * @param experienced true to learn whether the charm is learned on xp, false if interested in creation
   *                    learning.
   */
  @Override
  public boolean isLearned(Charm charm, boolean experienced) {
    if (experienced) {
      return charmsLearnedWithExperience.contains(charm);
    }
    return charmsLearnedOnCreation.contains(charm);
  }

  @Override
  public boolean isForgettable(Charm charm) {
    return !learnArbitrator.isCompulsiveCharm(charm) && learnStrategy.isUnlearnable(this, charm);
  }

  public void forgetAll(CategoryReference reference) {
    for (Charm charm : new HashSet<>(charmsLearnedWithExperience)) {
      if (reference.equals(charm.getTreeReference().category)) {
        forgetCharm(charm, true);
      }
    }
    for (Charm charm : new HashSet<>(charmsLearnedOnCreation)) {
      if (reference.equals(charm.getTreeReference().category)) {
        forgetCharm(charm, false);
      }
    }
  }

  private Charm[] getLearnedCoreCharms() {
    List<Charm> charms = new ArrayList<>();
    for (Charm charm : getAllLearnedCharms()) {
      if (!charm.hasAttribute(CharmAttributeList.EXCLUSIVE_ATTRIBUTE)) {
        charms.add(charm);
      }
    }
    return charms.toArray(new Charm[charms.size()]);
  }

  public void forgetExclusives(CategoryReference reference) {
    for (Charm charm : getAllLearnedExclusiveCharms()) {
      if (reference.equals(charm.getTreeReference().category)) {
        boolean isCreationLearned = charmsLearnedOnCreation.contains(charm);
        forgetCharm(charm, isCreationLearned);
      }
    }
  }

  private List<Charm> getAllLearnedExclusiveCharms() {
    List<Charm> exclusiveCharms = new ArrayList<>();
    exclusiveCharms.addAll(getAllLearnedCharms());
    exclusiveCharms.removeAll(asList(getLearnedCoreCharms()));
    return exclusiveCharms;
  }

  private List<Charm> getAllLearnedCharms() {
    List<Charm> allLearnedCharms = new ArrayList<>();
    allLearnedCharms.addAll(charmsLearnedOnCreation);
    allLearnedCharms.addAll(charmsLearnedWithExperience);
    return allLearnedCharms;
  }
}