package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.old.CharmAttributeList;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmLearnArbitrator;
import net.sf.anathema.hero.charms.model.learn.prerequisites.CharmsToForget;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.sf.anathema.hero.charms.model.learn.prerequisites.CollectPrerequisiteCharms.collectPrerequisiteCharms;

public class LearningCharmTreeImpl implements LearningCharmTree {

  private final Set<Charm> charmsLearnedOnCreation = new HashSet<>();
  private final Set<Charm> charmsLearnedWithExperience = new HashSet<>();
  private final Announcer<ICharmLearnListener> control = Announcer.to(ICharmLearnListener.class);
  private CharmTree charmTree;
  private final IExtendedCharmLearnableArbitrator learnArbitrator;
  private final ICharmLearnStrategy learnStrategy;
  private final ILearningCharmGroupContainer charmGroupContainer;

  public LearningCharmTreeImpl(ICharmLearnStrategy learnStrategy, CharmTree charmTree, IExtendedCharmLearnableArbitrator arbitrator,
                               ILearningCharmGroupContainer charmGroupContainer) {
    this.learnStrategy = learnStrategy;
    this.charmTree = charmTree;
    this.learnArbitrator = arbitrator;
    this.charmGroupContainer = charmGroupContainer;
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
      LearningCharmTree childGroup = charmGroupContainer.getLearningCharmGroup(child);
      childGroup.forgetCharm(child, experienced);
    }
  }

  private Set<Charm> getLearnFollowUpCharms(Charm charm, CharmLearnArbitrator learnArbitrator) {
    CharmsToForget charmsToForget = new CharmsToForget(charm, learnArbitrator);
    return charmsToForget.getLearnFollowUpCharms();
  }

  private void learnParents(Charm charm, boolean experienced) {
    for (Charm parent : collectPrerequisiteCharms(charm, learnArbitrator)) {
      LearningCharmTree parentGroup = charmGroupContainer.getLearningCharmGroup(parent);
      if (!parentGroup.isLearned(parent)) {
        parentGroup.learnCharm(parent, experienced);
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

  @Override
  public void forgetAll() {
    Set<Charm> forgetCloneCharms = new HashSet<>(charmsLearnedWithExperience);
    for (Charm charm : forgetCloneCharms) {
      forgetCharm(charm, true);
    }
    forgetCloneCharms = new HashSet<>(charmsLearnedOnCreation);
    for (Charm charm : forgetCloneCharms) {
      forgetCharm(charm, false);
    }
  }

  @Override
  public Charm[] getCoreCharms() {
    Charm[] allCharms = getAllCharms();
    List<Charm> charms = new ArrayList<>();
    for (Charm charm : allCharms) {
      if (!charm.hasAttribute(CharmAttributeList.EXCLUSIVE_ATTRIBUTE)) {
        charms.add(charm);
      }
    }
    return charms.toArray(new Charm[charms.size()]);
  }

  @Override
  public void forgetExclusives() {
    List<Charm> exclusiveCharms = new ArrayList<>();
    Collections.addAll(exclusiveCharms, getAllCharms());
    exclusiveCharms.removeAll(Arrays.asList(getCoreCharms()));
    for (Charm charm : exclusiveCharms) {
      forgetCharm(charm, isLearned(charm, true));
    }
  }

  @Override
  public Charm[] getAllCharms() {
    return charmTree.getAllCharms();
  }

  @Override
  public boolean isCharmFromTree(Charm charm) {
    return charmTree.isCharmFromTree(charm);
  }

  @Override
  public String getId() {
    return charmTree.getId();
  }

  @Override
  public TreeReference getReference() {
    return charmTree.getReference();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return getId();
  }
}