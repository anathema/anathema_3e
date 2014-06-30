package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.charm.old.cost.CostListImpl;
import net.sf.anathema.charm.old.source.SourceBook;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.charm.combos.ComboRestrictions;
import net.sf.anathema.hero.magic.charm.duration.SimpleDuration;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.SimpleCharmLearnPrerequisite;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CharmTest {

  @Test
  public void testParentCharmsNotOverwritten() throws Exception {
    DummyCharm dummy = new DummyCharm("OtherDummy");
    CharmImpl charm = createCharm(dummy);
    charm.extractParentCharms(new HashMap<>());
    assertEquals(1, charm.getPrerequisitesOfType(SimpleCharmLearnPrerequisite.class).size());
    assertEquals(dummy, charm.getPrerequisitesOfType(SimpleCharmLearnPrerequisite.class).toArray(new SimpleCharmLearnPrerequisite[1])[0].getDirectPredecessors()[0]);
  }

  @Test
  public void testCharmNoSource() throws Exception {
    ValuedTraitType[] prerequisites = new ValuedTraitType[]{new net.sf.anathema.hero.traits.model.types.ValuedTraitType(AbilityType.Archery, 5)};
    ValuedTraitType essence = new net.sf.anathema.hero.traits.model.types.ValuedTraitType(OtherTraitType.Essence, 3);
    CharmPrerequisiteList prerequisiteList =
            new CharmPrerequisiteList(prerequisites, essence, new CharmLearnPrerequisite[0]);
    try {
      new CharmImpl(new DummyExaltCharacterType(), "ATTRIBUTES", "Group", false, prerequisiteList, new CostListImpl(null, null, null, null),
              new ComboRestrictions(), SimpleDuration.getDuration("Duration"),
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
    CharmImpl charmImpl =
            new CharmImpl(new DummyExaltCharacterType(), "ATTRIBUTES", "Group", false, prerequisiteList, new CostListImpl(null, null, null, null),
                    new ComboRestrictions(), SimpleDuration.getDuration("Duration"),
                    CharmType.Simple, new SourceBook[0]);
    charmImpl.addParentCharms(parent);
    return charmImpl;
  }
}