package net.sf.anathema.hero.combat.model.social;

import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitType;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Performance;

public class PerformanceSocialAttack extends AbstractSocialAttack {

  public PerformanceSocialAttack(TraitMap collection) {
    super(collection);
  }

  @Override
  public int getRate() {
    return 1;
  }

  @Override
  public int getSpeed() {
    return 6;
  }

  @Override
  public TraitType getName() {
    return Performance;
  }
}