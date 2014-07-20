package net.sf.anathema.hero.spells.parser;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;

public interface SpellCache extends ExtensibleDataSet {
  Spells getSpells();
}
