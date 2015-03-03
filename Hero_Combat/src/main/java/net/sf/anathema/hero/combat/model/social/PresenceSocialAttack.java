package net.sf.anathema.hero.combat.model.social;

import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.types.AbilityType;

public class PresenceSocialAttack extends AbstractSocialAttack {

  public PresenceSocialAttack(TraitMap collection) {
    super(collection);
  }

  @Override
  public int getRate() {
    return 2;
  }

  @Override
  public int getSpeed() {
    return 4;
  }

  @Override
  public AbilityType getName() {
    return AbilityType.Presence;
  }
}