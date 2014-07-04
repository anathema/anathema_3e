package net.sf.anathema.magic.data;

import net.sf.anathema.charm.data.cost.CostList;
import net.sf.anathema.charm.data.reference.MagicName;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.magic.data.attribute.MagicAttribute;
import net.sf.anathema.magic.data.source.SourceBook;

public interface Magic {

  MagicName getName();

  MagicAttribute[] getAttributes();

  SourceBook[] getSources();

  CostList getTemporaryCost();

  boolean hasAttribute(Identifier attribute);
}