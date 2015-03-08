package net.sf.anathema.magic.template.prerequisite;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CharmName;

public interface CharmMap {

  Charm get(CharmName charmName);
}