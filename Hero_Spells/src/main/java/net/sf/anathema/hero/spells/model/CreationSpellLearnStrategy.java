package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;

import java.util.List;

public class CreationSpellLearnStrategy implements ISpellLearnStrategy {

  @Override
  public void addSpells(SpellsModel configuration, Spells addedSpells) {
    configuration.addSpells(addedSpells, false);
  }

  @Override
  public void removeSpells(SpellsModel configuration, Spells removedSpells) {
    configuration.removeSpells(removedSpells, false);
  }

  @Override
  public boolean isSpellAllowed(SpellsModel configuration, Spell spell) {
    return configuration.isSpellAllowed(spell, false);
  }

  @Override
  public Spells getLearnedSpells(SpellsModel configuration) {
    return configuration.getLearnedSpells(false);
  }

  @Override
  public boolean isLearned(SpellsModel configuration, Spell spell) {
    return configuration.isLearnedOnCreation(spell);
  }
}