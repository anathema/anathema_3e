package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import net.sf.anathema.hero.charms.model.context.CreationCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnStrategy;
import net.sf.anathema.hero.charms.model.learn.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.learn.LearningCharmTreeImpl;
import net.sf.anathema.hero.charms.model.options.CharmTreeCategoryImpl;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.magic.dummy.DummyLearnableArbitrator;
import net.sf.anathema.hero.magic.dummy.DummyLearningCharmGroupContainer;
import org.junit.Test;

import static net.sf.anathema.hero.traits.model.types.AbilityType.Melee;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LearningCharmTreeImplTest {

  private static final CategoryReference ANY_CATEGORY_REFERENCE = new CategoryReference("AnyCategory");
  private DummyLearningCharmGroupContainer container = new DummyLearningCharmGroupContainer();

  @Test
  public void testIsLearnedCreationCharmOnCreation() throws Exception {
    Charm learnableCharm = new DummyCharm("learnableDummyCharm");
    IExtendedCharmLearnableArbitrator learnableArbitrator = new DummyLearnableArbitrator(learnableCharm.getName().text);
    LearningCharmTreeImpl learningCharmGroup = createMeleeTree(learnableArbitrator);
    container.setLearningCharmGroup(learningCharmGroup);
    assertFalse(learningCharmGroup.isLearned(learnableCharm));
    learningCharmGroup.toggleLearned(learnableCharm);
    assertTrue(learningCharmGroup.isLearned(learnableCharm));
  }

  @Test
  public void testLearnedCreationCharmsUnlearnableOnCreation() throws Exception {
    Charm learnableCharm = new DummyCharm("learnableDummyCharm");
    IExtendedCharmLearnableArbitrator learnableArbitrator = new DummyLearnableArbitrator(learnableCharm.getName().text);
    LearningCharmTreeImpl learningCharmGroup = createMeleeTree(learnableArbitrator);
    container.setLearningCharmGroup(learningCharmGroup);
    assertFalse(learningCharmGroup.isForgettable(learnableCharm));
    learningCharmGroup.toggleLearned(learnableCharm);
    assertTrue(learningCharmGroup.isForgettable(learnableCharm));
  }

  private LearningCharmTreeImpl createMeleeTree(IExtendedCharmLearnableArbitrator learnableArbitrator) {
    return createTree(learnableArbitrator, new TreeName(Melee.getId()));
  }

  private LearningCharmTreeImpl createTree(IExtendedCharmLearnableArbitrator learnableArbitrator, TreeName treeName) {
    ICharmLearnStrategy learnStrategy = new CreationCharmLearnStrategy();
    CharmTreeCategoryImpl treeCategory = DummyCharmTreeCategory.Create(ANY_CATEGORY_REFERENCE);
    TreeReference reference = new TreeReference(ANY_CATEGORY_REFERENCE, treeName);
    CharmTreeImpl group = new CharmTreeImpl(reference,
            treeCategory.getAllCharmsForTree(treeName).toArray(new Charm[treeCategory.getAllCharmsForTree(treeName).size()]));
    return new LearningCharmTreeImpl(learnStrategy, group, learnableArbitrator, container);
  }

  private LearningCharmTreeImpl createTree(IExtendedCharmLearnableArbitrator learnableArbitrator,
                                           CharmTreeCategoryImpl treeCategory, TreeName treeName) {
    ICharmLearnStrategy learnSrategy = new CreationCharmLearnStrategy();
    TreeReference reference = new TreeReference(ANY_CATEGORY_REFERENCE, treeName);
    CharmTreeImpl group = new CharmTreeImpl(reference,
            treeCategory.getAllCharmsForTree(treeName).toArray(new Charm[treeCategory.getAllCharmsForTree(treeName).size()]));
    return new LearningCharmTreeImpl(learnSrategy, group, learnableArbitrator, container);
  }
}