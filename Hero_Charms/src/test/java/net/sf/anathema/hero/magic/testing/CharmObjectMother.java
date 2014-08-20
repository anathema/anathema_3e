package net.sf.anathema.hero.magic.testing;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class CharmObjectMother {

  public static Charm createMartialArtsCharm() {
    Charm charm = mock(Charm.class);
    when(charm.getName()).thenReturn(new CharmName("AnyName"));
    when(charm.hasAttribute(new SimpleIdentifier("MartialArts"))).thenReturn(true);
    return charm;
  }
}
