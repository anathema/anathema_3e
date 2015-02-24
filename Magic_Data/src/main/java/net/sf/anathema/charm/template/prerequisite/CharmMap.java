package net.sf.anathema.charm.template.prerequisite;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;

public interface CharmMap {

  Charm get(CharmName charmName);
}