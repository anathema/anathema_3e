package net.sf.anathema.hero.dummy.magic;

import net.sf.anathema.charm.data.reference.SpellName;
import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.charm.old.cost.CostList;
import net.sf.anathema.charm.old.source.SourceBook;
import net.sf.anathema.hero.magic.spells.CircleType;
import net.sf.anathema.hero.magic.spells.Spell;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identifier;

public class DummySpell implements Spell {

  public DummySpell() {
    // nothing to do
  }
  @Override
  public CircleType getCircleType() {
    return null;
  }

  @Override
  public SpellName getName() {
    return null;
  }

  @Override
  public MagicAttribute[] getAttributes() {
    return new MagicAttribute[0];
  }

  @Override
  public SourceBook[] getSources() {
    throw new NotYetImplementedException();
  }

  @Override
  public CostList getTemporaryCost() {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean hasAttribute(Identifier attribute) {
    return false;
  }

  @Override
  public String getAttributeValue(Identifier attribute) {
    return null;
  }

  @Override
  public String getTarget() {
    throw new NotYetImplementedException();
  }
}