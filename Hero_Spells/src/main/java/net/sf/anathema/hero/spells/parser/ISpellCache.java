package net.sf.anathema.hero.spells.parser;

import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;

public interface ISpellCache extends ExtensibleDataSet {
  Spell[] getSpells();
}
