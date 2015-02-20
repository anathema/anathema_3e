package net.sf.anathema.hero.combat.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.hero.sheet.pdf.content.stats.HeroStatsModifiers;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

public class CharacterUtilities {

  public static int getSocialAttackValue(TraitMap traitMap, TraitType... types) {
    return getTotalValue(traitMap, types);
  }

  public static int getJoinBattle(TraitMap traitMap, HeroStatsModifiers equipment) {
    int baseValue = getTotalValue(traitMap, AttributeType.Wits, AbilityType.Awareness);
    return Math.max(baseValue, 1);
  }

  public static int getJoinBattleWithSpecialty(TraitMap traitMap, HeroStatsModifiers equipment, int awarenessSpecialty) {
    int baseValue = getJoinBattle(traitMap, equipment);
    baseValue += awarenessSpecialty;
    return Math.max(baseValue, 1);
  }

  public static int getKnockdownThreshold(TraitMap traitMap) {
    int baseValue = getTotalValue(traitMap, AttributeType.Stamina, AbilityType.Resistance);
    return Math.max(baseValue, 0);
  }

  public static int getKnockdownPool(Hero hero) {
    return getKnockdownPool(TraitModelFetcher.fetch(hero));
  }

  public static int getKnockdownPool(TraitMap traitMap) {
    int attribute = getMaxValue(traitMap, AttributeType.Dexterity, AttributeType.Stamina);
    int ability = getMaxValue(traitMap, AbilityType.Athletics, AbilityType.Resistance);
    int pool = attribute + ability;
    return Math.max(pool, 0);
  }

  public static int getStunningThreshold(TraitMap traitMap) {
    int baseValue = getTotalValue(traitMap, AttributeType.Stamina);
    return Math.max(baseValue, 0);
  }

  public static int getStunningPool(TraitMap traitMap) {
    int baseValue = getTotalValue(traitMap, AttributeType.Stamina, AbilityType.Resistance);
    return Math.max(baseValue, 0);
  }

  private static int getMaxValue(TraitMap traitMap, TraitType second, TraitType first) {
    return Math.max(traitMap.getTrait(first).getCurrentValue(), traitMap.getTrait(second).getCurrentValue());
  }

  private static int getTotalValue(TraitMap traitCollection, TraitType... types) {
    int sum = 0;
    for (TraitType type : types) {
      sum += traitCollection.getTrait(type).getCurrentValue();
    }
    return sum;
  }

  private static int getDodgeDvPool(TraitMap traitCollection) {
    int essence = traitCollection.getTrait(OtherTraitType.Essence).getCurrentValue();
    int dvPool = getTotalValue(traitCollection, AttributeType.Dexterity, AbilityType.Dodge);
    if (essence >= 2) {
      dvPool += essence;
    }
    return dvPool;
  }

  private static int getRoundedDodgeDv(HeroType heroType, int dvPool) {
    int dv;
    if (heroType.isEssenceUser()) {
      dv = (int) Math.ceil(dvPool * 0.5);
    } else {
      dv = dvPool / 2;
    }
    return dv;
  }

  public static int getDodgeDv(HeroType heroType, TraitMap traitMap, HeroStatsModifiers modifiers) {
    return getDodgeDvWithSpecialty(heroType, traitMap, modifiers, 0);
  }

  public static int getDodgeDvWithSpecialty(HeroType heroType, TraitMap traitMap, HeroStatsModifiers equipment, int specialty) {
    int dvPool = getDodgeDvPool(traitMap) + specialty;
    int dv = getRoundedDodgeDv(heroType, dvPool) + equipment.getMobilityPenalty();
    return Math.max(dv, 0);
  }
}