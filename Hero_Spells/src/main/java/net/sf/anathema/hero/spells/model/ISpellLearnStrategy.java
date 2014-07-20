package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;

import java.util.List;

public interface ISpellLearnStrategy {

  void addSpells(SpellsModel configuration, Spells addedSpells);

  void removeSpells(SpellsModel configuration, Spells removedSpells);

  boolean isSpellAllowed(SpellsModel configuration, Spell spell);

  Spells getLearnedSpells(SpellsModel configuration);

  boolean isLearned(SpellsModel configuration, Spell spell);
}