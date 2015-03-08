package net.sf.anathema.hero.spells.advance;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.magic.advance.experience.MagicExperienceData;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.model.IsFavoredSpell;

public class SpellExperienceCostCalculator {

  private final MagicExperienceData costs;

  public SpellExperienceCostCalculator(MagicExperienceData costs) {
    this.costs = costs;
  }

  public int getSpellCosts(Hero hero, Spell spell) {
    return costs.getMagicCosts(spell, new IsFavoredSpell(hero));
  }
}