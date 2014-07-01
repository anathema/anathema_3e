package net.sf.anathema.hero.magic.basic;

import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.charm.old.cost.CostList;
import net.sf.anathema.charm.old.source.SourceBook;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.util.Identifier;

public interface Magic {

  String getId();

  MagicAttribute[] getAttributes();

  SourceBook[] getSources();

  SourceBook getPrimarySource();

  CostList getTemporaryCost();

  boolean isFavored(Hero hero);

  boolean hasAttribute(Identifier attribute);

  String getAttributeValue(Identifier attribute);
}