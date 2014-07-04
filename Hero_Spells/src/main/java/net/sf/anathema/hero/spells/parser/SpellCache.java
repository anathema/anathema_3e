package net.sf.anathema.hero.spells.parser;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.spells.data.Spell;

public interface SpellCache extends ExtensibleDataSet {
  Spell[] getSpells();
}
