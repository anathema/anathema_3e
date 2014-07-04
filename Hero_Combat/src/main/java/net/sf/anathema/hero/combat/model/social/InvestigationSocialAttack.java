package net.sf.anathema.hero.combat.model.social;

import net.sf.anathema.hero.sheet.pdf.content.stats.HeroStatsModifiers;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.types.AbilityType;

public class InvestigationSocialAttack extends AbstractSocialAttack {

  public InvestigationSocialAttack(TraitMap collection, HeroStatsModifiers equipmentModifiers) {
    super(collection, equipmentModifiers);
  }

  @Override
  public int getRate() {
    return 2;
  }

  @Override
  public int getSpeed() {
    return 5;
  }

  @Override
  public AbilityType getName() {
    return AbilityType.Investigation;
  }
}