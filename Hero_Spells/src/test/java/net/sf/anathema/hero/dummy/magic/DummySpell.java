package net.sf.anathema.hero.dummy.magic;

import net.sf.anathema.charm.data.cost.CostList;
import net.sf.anathema.charm.data.reference.SpellName;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.library.exception.NotYetImplementedException;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.magic.data.attribute.MagicAttribute;
import net.sf.anathema.magic.data.source.SourceBook;

import java.util.List;

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
  public List<SourceBook> getSources() {
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
  public String getTarget() {
    throw new NotYetImplementedException();
  }
}