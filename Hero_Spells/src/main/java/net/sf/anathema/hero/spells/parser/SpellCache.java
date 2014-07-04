package net.sf.anathema.hero.spells.parser;

import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.spells.data.Spell;

public interface SpellCache extends ExtensibleDataSet {
  Spell[] getSpells();
}
