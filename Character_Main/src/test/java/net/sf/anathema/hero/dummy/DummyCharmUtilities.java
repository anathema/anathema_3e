package net.sf.anathema.hero.dummy;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;

public class DummyCharmUtilities {

  public static Charm createCharm(CharmType charmType) {
    return new DummyCharm("Instant", charmType, null);
  }

  public static Charm createCharm(CharmType charmType, ValuedTraitType prerequisite) {
    return new DummyCharm("Instant", charmType, new ValuedTraitType[]{prerequisite});
  }

  public static Charm createCharm(String duration) {
    return new DummyCharm(duration, CharmType.Reflexive, null);
  }

  public static Charm createCharm(String id, String groupId) {
    DummyCharm dummyCharm = new DummyCharm(id);
    dummyCharm.setGroupId(groupId);
    return dummyCharm;
  }
}