package net.sf.anathema.hero.magic.testing;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharmObjectMother {

  public static Charm createMartialArtsCharm() {
    Charm charm = mock(Charm.class);
    when(charm.getName()).thenReturn(new CharmName("AnyName"));
    when(charm.hasAttribute(new SimpleIdentifier("MartialArts"))).thenReturn(true);
    return charm;
  }
}
