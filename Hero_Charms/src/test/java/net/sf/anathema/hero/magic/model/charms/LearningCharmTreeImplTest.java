package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.charm.data.reference.TreeCategory;
import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import net.sf.anathema.hero.charms.model.context.CreationCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnStrategy;
import net.sf.anathema.hero.charms.model.learn.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.learn.LearningCharmTree;
import net.sf.anathema.hero.charms.model.learn.LearningCharmTreeImpl;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.dummy.DummyLearnableArbitrator;
import net.sf.anathema.hero.magic.dummy.DummyLearningCharmGroupContainer;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LearningCharmTreeImplTest {

  private DummyLearningCharmGroupContainer container = new DummyLearningCharmGroupContainer();

  private LearningCharmTreeImpl createSolarMeleeGroup(IExtendedCharmLearnableArbitrator learnableArbitrator) {
    return createSolarGroup(learnableArbitrator, AbilityType.Melee.getId());
  }

  private LearningCharmTreeImpl createSolarGroup(IExtendedCharmLearnableArbitrator learnableArbitrator, String groupId) {
    DummyExaltCharacterType type = new DummyExaltCharacterType();
    ICharmLearnStrategy learnStrategy = new CreationCharmLearnStrategy();
    DummyCharmTreeCategory charmTree = new DummyCharmTreeCategory(new TreeCategory(type.getId()));
    CharmTreeImpl group = new CharmTreeImpl(type, groupId,
            charmTree.getAllCharmsForGroup(groupId).toArray(new Charm[charmTree.getAllCharmsForGroup(groupId).size()]));
    return new LearningCharmTreeImpl(learnStrategy, group, learnableArbitrator, container);
  }

  private LearningCharmTreeImpl createSolarGroup(IExtendedCharmLearnableArbitrator learnableArbitrator,
                                                 DummyCharmTreeCategory charmTree, String groupId) {
    ICharmLearnStrategy learnSrategy = new CreationCharmLearnStrategy();
    CharmTreeImpl group = new CharmTreeImpl(new DummyExaltCharacterType(), groupId,
            charmTree.getAllCharmsForGroup(groupId).toArray(new Charm[charmTree.getAllCharmsForGroup(groupId).size()]));
    return new LearningCharmTreeImpl(learnSrategy, group, learnableArbitrator, container);
  }

  @Test
  public void testIsLearnedCreationCharmOnCreation() throws Exception {
    Charm learnableCharm = new DummyCharm("learnableDummyCharm");
    IExtendedCharmLearnableArbitrator learnableArbitrator = new DummyLearnableArbitrator(learnableCharm.getId());
    LearningCharmTreeImpl learningCharmGroup = createSolarMeleeGroup(learnableArbitrator);
    container.setLearningCharmGroup(learningCharmGroup);
    assertFalse(learningCharmGroup.isLearned(learnableCharm));
    learningCharmGroup.toggleLearned(learnableCharm);
    assertTrue(learningCharmGroup.isLearned(learnableCharm));
  }

  @Test
  public void testLearnedCreationCharmsUnlearnableOnCreation() throws Exception {
    Charm learnableCharm = new DummyCharm("learnableDummyCharm");
    IExtendedCharmLearnableArbitrator learnableArbitrator = new DummyLearnableArbitrator(learnableCharm.getId());
    LearningCharmTreeImpl learningCharmGroup = createSolarMeleeGroup(learnableArbitrator);
    container.setLearningCharmGroup(learningCharmGroup);
    assertFalse(learningCharmGroup.isForgettable(learnableCharm));
    learningCharmGroup.toggleLearned(learnableCharm);
    assertTrue(learningCharmGroup.isForgettable(learnableCharm));
  }

  @Test
  public void testMultipleGroupsPrerequisiteCharms() throws Exception {
    String internalPrerequisiteId = "internalPrerquisite";
    String externalPrerequisiteId = "externalPrerquisite";
    String learCharmID = "learnCharm";
    DummyCharm internalPrerequisite =
            new DummyCharm(internalPrerequisiteId, new Charm[0], new ValuedTraitType[]{new net.sf.anathema.hero.traits.model.types.ValuedTraitType(AbilityType.Melee, 1)});
    DummyCharm externalPrerequisite =
            new DummyCharm(externalPrerequisiteId, new Charm[0], new ValuedTraitType[]{new net.sf.anathema.hero.traits.model.types.ValuedTraitType(AbilityType.Archery, 1)});
    DummyCharm learnCharm = new DummyCharm(learCharmID, new Charm[]{internalPrerequisite, externalPrerequisite},
            new ValuedTraitType[]{new net.sf.anathema.hero.traits.model.types.ValuedTraitType(AbilityType.Melee, 1)});
    DummyCharmTreeCategory charmTree = new DummyCharmTreeCategory(null, internalPrerequisite, externalPrerequisite, learnCharm);
    externalPrerequisite.addLearnFollowUpCharm(learnCharm);
    IExtendedCharmLearnableArbitrator learnableArbitrator =
            new DummyLearnableArbitrator(externalPrerequisiteId, internalPrerequisiteId, learCharmID);
    LearningCharmTreeImpl internalGroup = createSolarGroup(learnableArbitrator, charmTree, AbilityType.Melee.getId());
    LearningCharmTreeImpl externalGroup = createSolarGroup(learnableArbitrator, charmTree, AbilityType.Archery.getId());
    container.setLearningCharmGroups(new LearningCharmTree[]{internalGroup, externalGroup});
    assertFalse(externalGroup.isLearned(externalPrerequisite));
    assertFalse(internalGroup.isLearned(internalPrerequisite));
    assertFalse(internalGroup.isLearned(learnCharm));
    internalGroup.learnCharm(learnCharm, false);
    assertTrue(externalGroup.isLearned(externalPrerequisite));
    assertTrue(internalGroup.isLearned(internalPrerequisite));
    assertTrue(internalGroup.isLearned(learnCharm));
    externalGroup.forgetCharm(externalPrerequisite, false);
    assertFalse(externalGroup.isLearned(externalPrerequisite));
    assertTrue(internalGroup.isLearned(internalPrerequisite));
    assertFalse(internalGroup.isLearned(learnCharm));
  }

}