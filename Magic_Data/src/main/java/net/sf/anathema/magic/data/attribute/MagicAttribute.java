package net.sf.anathema.magic.data.attribute;

import net.sf.anathema.library.identifier.Identifier;

public interface MagicAttribute extends Identifier {

  boolean isVisualized();
  
  String getValue();
}