package net.sf.anathema.charm.old.attribute;

import net.sf.anathema.lib.util.Identifier;

public interface MagicAttribute extends Identifier {

  boolean isVisualized();
  
  String getValue();
}