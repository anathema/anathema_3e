package net.sf.anathema.charm.data;

import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface CharmAttributeList {
  
  Identifier ECLIPSE_ATTRIBUTE = new SimpleIdentifier("Eclipse");
  Identifier IGNORES_SUPERNAL = new SimpleIdentifier("IgnoresSupernal");
}