package net.sf.anathema.hero.magic.charm;

import net.sf.anathema.charm.data.reference.CharmName;

public interface UnlinkedCharmMap {

  CharmImpl get(CharmName charmName);
}
