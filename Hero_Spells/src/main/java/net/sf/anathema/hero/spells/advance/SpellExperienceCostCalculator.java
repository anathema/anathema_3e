package net.sf.anathema.hero.spells.advance;

import net.sf.anathema.hero.charms.advance.costs.CostAnalyzerImpl;
import net.sf.anathema.hero.charms.advance.experience.MagicExperienceData;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.data.Spell;

public class SpellExperienceCostCalculator {

  private final MagicExperienceData costs;

  public SpellExperienceCostCalculator(MagicExperienceData costs) {
    this.costs = costs;
  }

  public int getSpellCosts(Hero hero, Spell spell) {
    return costs.getMagicCosts(spell, new CostAnalyzerImpl(hero));
  }
}