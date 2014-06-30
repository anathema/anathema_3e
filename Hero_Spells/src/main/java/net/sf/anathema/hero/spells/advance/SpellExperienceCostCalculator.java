package net.sf.anathema.hero.spells.advance;

import net.sf.anathema.hero.magic.spells.Spell;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzerImpl;
import net.sf.anathema.hero.charms.advance.experience.MagicExperienceData;
import net.sf.anathema.hero.model.Hero;

public class SpellExperienceCostCalculator {

  private final MagicExperienceData costs;

  public SpellExperienceCostCalculator(MagicExperienceData costs) {
    this.costs = costs;
  }

  public int getSpellCosts(Hero hero, Spell spell) {
    return costs.getMagicCosts(spell, new CostAnalyzerImpl(hero));
  }
}