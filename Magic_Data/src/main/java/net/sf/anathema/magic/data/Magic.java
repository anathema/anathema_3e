package net.sf.anathema.magic.data;

import net.sf.anathema.charm.data.cost.CostList;
import net.sf.anathema.charm.data.reference.MagicName;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.magic.data.attribute.MagicAttribute;
import net.sf.anathema.magic.data.source.SourceBook;

import java.util.List;

public interface Magic {

  MagicName getName();

  Iterable<MagicAttribute> getAttributes();

  List<SourceBook> getSources();

  CostList getTemporaryCost();

  boolean hasAttribute(Identifier attribute);
}