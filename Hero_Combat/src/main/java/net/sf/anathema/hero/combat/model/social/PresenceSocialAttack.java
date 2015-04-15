package net.sf.anathema.hero.combat.model.social;

import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.CommonTraitTypes;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Presence;

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
  public TraitType getName() {
    return Presence;
  }
}