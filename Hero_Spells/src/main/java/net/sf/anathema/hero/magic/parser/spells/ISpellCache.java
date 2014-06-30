package net.sf.anathema.hero.magic.parser.spells;

import net.sf.anathema.hero.magic.spells.Spell;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;

public interface ISpellCache extends ExtensibleDataSet {
  Spell[] getSpells();
}
