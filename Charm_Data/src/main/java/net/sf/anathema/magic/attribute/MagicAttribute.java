package net.sf.anathema.magic.attribute;

import net.sf.anathema.lib.util.Identifier;

public interface MagicAttribute extends Identifier {

  boolean isVisualized();
  
  String getValue();
}