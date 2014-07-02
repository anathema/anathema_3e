package net.sf.anathema.hero.magic.testing;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.lib.util.SimpleIdentifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharmObjectMother {

  public static Charm createMartialArtsCharm(MartialArtsLevel level) {
    Charm charm = mock(Charm.class);
    when(charm.getName()).thenReturn(new CharmName("AnyName"));
    when(charm.hasAttribute(new SimpleIdentifier("MartialArts"))).thenReturn(true);
    when(charm.hasAttribute(new SimpleIdentifier(level.getId()))).thenReturn(true);
    return charm;
  }
}
