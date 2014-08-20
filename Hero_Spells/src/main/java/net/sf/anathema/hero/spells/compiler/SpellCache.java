package net.sf.anathema.hero.spells.compiler;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.spells.data.Spells;

public interface SpellCache extends ExtensibleDataSet {
  Spells getSpells();
}
