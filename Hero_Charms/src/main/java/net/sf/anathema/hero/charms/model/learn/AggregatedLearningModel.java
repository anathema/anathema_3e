package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.library.collection.MultiEntryMap;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class AggregatedLearningModel implements LearningModel {

  private final Announcer<ICharmLearnListener> announcer = new Announcer<>(ICharmLearnListener.class);
  private final Map<TreeName, LearningCharmTree> learnTreesByTreeName = new HashMap<>();
  private final MultiEntryMap<CategoryReference, LearningCharmTree> learnTreesByCategory = new MultiEntryMap<>();

  public void addModel(LearningCharmTree tree) {
    learnTreesByTreeName.put(tree.getReference().name, tree);
    learnTreesByCategory.add(tree.getReference().category, tree);
    tree.addCharmLearnListener(new ICharmLearnListener() {
      @Override
      public void charmLearned(Charm charm) {
        announcer.announce().charmLearned(charm);
      }

      @Override
      public void charmForgotten(Charm charm) {
        announcer.announce().charmForgotten(charm);
      }

      @Override
      public void charmNotLearnable(Charm charm) {
        announcer.announce().charmNotLearnable(charm);
      }

      @Override
      public void charmNotUnlearnable(Charm charm) {
        announcer.announce().charmNotUnlearnable(charm);
      }

      @Override
      public void recalculateRequested() {
        announcer.announce().recalculateRequested();
      }
    });
  }

  @Override
  public void toggleLearned(Charm charm) {
    getTreeFor(charm).toggleLearned(charm);
  }

  private LearningCharmTree getTreeFor(Charm charm) {
    return learnTreesByTreeName.get(charm.getTreeReference().name);
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    announcer.addListener(listener);
  }

  @Override
  public Charm[] getCreationLearnedCharms() {
    List<Charm> learnedCharms = new ArrayList<>();
    learnTreesByTreeName.values().forEach(tree -> learnedCharms.addAll(asList(tree.getCreationLearnedCharms())));
    return learnedCharms.toArray(new Charm[learnedCharms.size()]);
  }

  @Override
  public void learnCharm(Charm charm, boolean experienced) {
    getTreeFor(charm).learnCharm(charm, experienced);
  }

  @Override
  public void learnCharmNoParents(Charm charm, boolean experienced, boolean announce) {
    getTreeFor(charm).learnCharmNoParents(charm, experienced, announce);
  }

  @Override
  public boolean isForgettable(Charm charm) {
    return getTreeFor(charm).isCharmFromTree(charm);
  }

  @Override
  public Charm[] getExperienceLearnedCharms() {
    List<Charm> learnedCharms = new ArrayList<>();
    learnTreesByTreeName.values().forEach(tree -> learnedCharms.addAll(asList(tree.getExperienceLearnedCharms())));
    return learnedCharms.toArray(new Charm[learnedCharms.size()]);
  }

  @Override
  public void forgetCharm(Charm child, boolean experienced) {
    getTreeFor(child).forgetCharm(child, experienced);
  }

  @Override
  public void forgetAll() {
    learnTreesByTreeName.values().forEach(LearningCharmTree::forgetAll);
  }

  @Override
  public void forgetExclusives() {
    learnTreesByTreeName.values().forEach(LearningCharmTree::forgetExclusives);
  }

  @Override
  public void fireRecalculateRequested() {
    learnTreesByTreeName.values().forEach(LearningCharmTree::fireRecalculateRequested);
  }

  @Override
  public boolean isLearned(Charm charm) {
    for (LearningCharmTree tree : learnTreesByTreeName.values()) {
      if (tree.isLearned(charm)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isLearned(Charm charm, boolean experienced) {
    for (LearningCharmTree tree : learnTreesByTreeName.values()) {
      if (tree.isLearned(charm, experienced)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void toggleLearnedOnCreation(Charm charm) {
    learnTreesByTreeName.values().forEach((charmTree) -> charmTree.toggleLearnedOnCreation(charm));
  }

  @Override
  public void toggleExperienceLearnedCharm(Charm charm) {
    learnTreesByTreeName.values().forEach((charmTree) -> charmTree.toggleLearnedOnCreation(charm));
  }

  public void forgetAll(CategoryReference reference) {
    for (LearningCharmTree learnTree : learnTreesByCategory.get(reference)) {
      learnTree.forgetAll();
    }
  }

  public void forgetExclusives(CategoryReference reference) {
    for (LearningCharmTree learnTree : learnTreesByCategory.get(reference)) {
      learnTree.forgetExclusives();
    }
  }
}
