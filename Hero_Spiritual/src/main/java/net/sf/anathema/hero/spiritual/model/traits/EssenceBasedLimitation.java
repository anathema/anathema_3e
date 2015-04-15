package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitLimitation;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Essence;

public class EssenceBasedLimitation implements TraitLimitation {

  @Override
  public int getAbsoluteLimit(Hero hero) {
    SpiritualTraitModel spiritualTraitModel = getOtherTraitModel(hero);
    TraitLimitation essenceLimitation = spiritualTraitModel.getEssenceLimitation();
    int essenceMaximum = essenceLimitation.getAbsoluteLimit(hero);
    return Math.max(essenceMaximum, 5);
  }

  @Override
  public int getCurrentMaximum(Hero hero, boolean modified) {
    SpiritualTraitModel spiritualTraitModel = getOtherTraitModel(hero);
    Trait essence = spiritualTraitModel.getTrait(Essence);
    int currentEssence = Math.min(essence.getCurrentValue(), spiritualTraitModel.getEssenceCap(modified));
    int currentEssenceValue = Math.max(currentEssence, 5);
    return Math.min(getAbsoluteLimit(hero), currentEssenceValue);
  }

  private SpiritualTraitModel getOtherTraitModel(Hero hero) {
    return SpiritualTraitModelFetcher.fetch(hero);
  }

  @SuppressWarnings("CloneDoesntDeclareCloneNotSupportedException")
  @Override
  public EssenceBasedLimitation clone() {
    try {
      return (EssenceBasedLimitation) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
}