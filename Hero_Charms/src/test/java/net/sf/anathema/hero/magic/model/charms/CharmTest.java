package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.old.cost.CostListImpl;
import net.sf.anathema.charm.old.source.SourceBook;
import net.sf.anathema.hero.charms.compiler.UnlinkedCharms;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.charm.duration.SimpleDuration;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.SimpleCharmPrerequisite;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class CharmTest {

  @Test
  public void testParentCharmsNotOverwritten() throws Exception {
    DummyCharm dummy = new DummyCharm("OtherDummy");
    CharmImpl charm = createCharm(dummy);
    UnlinkedCharms unlinkedCharms = mock(UnlinkedCharms.class);
    charm.extractParentCharms(unlinkedCharms);
    assertEquals(1, charm.getPrerequisitesOfType(SimpleCharmPrerequisite.class).size());
    assertEquals(dummy, charm.getPrerequisitesOfType(SimpleCharmPrerequisite.class).toArray(new SimpleCharmPrerequisite[1])[0].getDirectPredecessors()[0]);
  }

  @Test
  public void testCharmNoSource() throws Exception {
    ValuedTraitType[] prerequisites = new ValuedTraitType[]{new net.sf.anathema.hero.traits.model.types.ValuedTraitType(AbilityType.Archery, 5)};
    ValuedTraitType essence = new net.sf.anathema.hero.traits.model.types.ValuedTraitType(OtherTraitType.Essence, 3);
    CharmPrerequisiteList prerequisiteList =
            new CharmPrerequisiteList(prerequisites, essence, new CharmLearnPrerequisite[0]);
    try {
      TreeReference treeReference = new TreeReference(new CategoryReference("Category"), new TreeName("Tree"));
      new CharmImpl(treeReference, new CharmName("ATTRIBUTES"), prerequisiteList, new CostListImpl(null, null, null, null),
               SimpleDuration.getDuration("Duration"),
              CharmType.Simple, null);
      fail();
    } catch (NullPointerException e) {
      // Nothing to do
    }
  }

  private CharmImpl createCharm(DummyCharm parent) {
    ValuedTraitType[] prerequisites = new ValuedTraitType[]{new net.sf.anathema.hero.traits.model.types.ValuedTraitType(AbilityType.Archery, 5)};
    ValuedTraitType essence = new net.sf.anathema.hero.traits.model.types.ValuedTraitType(OtherTraitType.Essence, 3);
    CharmPrerequisiteList prerequisiteList =
            new CharmPrerequisiteList(prerequisites, essence, new CharmLearnPrerequisite[0]);
    TreeReference treeReference = new TreeReference(new CategoryReference("Category"), new TreeName("Tree"));
    CharmImpl charmImpl =
            new CharmImpl(treeReference, new CharmName("ATTRIBUTES"), prerequisiteList, new CostListImpl(null, null, null, null),
                    SimpleDuration.getDuration("Duration"), CharmType.Simple, new SourceBook[0]);
    charmImpl.addParentCharms(parent);
    return charmImpl;
  }
}