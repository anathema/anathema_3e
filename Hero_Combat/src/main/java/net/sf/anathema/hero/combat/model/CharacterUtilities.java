package net.sf.anathema.hero.combat.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.hero.sheet.pdf.content.stats.HeroStatsModifiers;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.TraitTypeList;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AttributeType;

import static net.sf.anathema.hero.traits.model.types.AttributeType.Dexterity;
import static net.sf.anathema.hero.traits.model.types.AttributeType.Stamina;
import static net.sf.anathema.hero.traits.model.types.AttributeType.Wits;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Essence;

public class CharacterUtilities {

  public static int getSocialAttackValue(TraitMap traitMap, TraitType... types) {
    return getTotalValue(traitMap, types);
  }

  public static int getJoinBattle(TraitMap traitMap) {
    TraitTypeList typeList = new TraitTypeList();
    typeList.add(Wits);
    typeList.add("Awareness");
    int baseValue = getTotalValue(traitMap, typeList);
    return Math.max(baseValue, 1);
  }

  public static int getJoinBattleWithSpecialty(TraitMap traitMap, int awarenessSpecialty) {
    int baseValue = getJoinBattle(traitMap);
    baseValue += awarenessSpecialty;
    return Math.max(baseValue, 1);
  }

  public static int getKnockdownThreshold(TraitMap traitMap) {
    TraitTypeList typeList = new TraitTypeList();
    typeList.add(Stamina);
    typeList.add("Resistance");
    int baseValue = getTotalValue(traitMap, typeList);
    return Math.max(baseValue, 0);
  }

  public static int getKnockdownPool(Hero hero) {
    return getKnockdownPool(TraitModelFetcher.fetch(hero));
  }

  public static int getKnockdownPool(TraitMap traitMap) {
    int attribute = getMaxValue(traitMap, Dexterity, AttributeType.Stamina);
    TraitTypeFinder finder = new TraitTypeFinder();
    int ability = getMaxValue(traitMap, finder.getTrait("Athletics"), finder.getTrait("Resistance"));
    int pool = attribute + ability;
    return Math.max(pool, 0);
  }

  public static int getStunningThreshold(TraitMap traitMap) {
    int baseValue = getTotalValue(traitMap, AttributeType.Stamina);
    return Math.max(baseValue, 0);
  }

  public static int getStunningPool(TraitMap traitMap) {
    TraitTypeList typeList = new TraitTypeList();
    typeList.add(Stamina);
    typeList.add("Resistance");
    int baseValue = getTotalValue(traitMap, typeList);
    return Math.max(baseValue, 0);
  }

  private static int getMaxValue(TraitMap traitMap, TraitType second, TraitType first) {
    return Math.max(traitMap.getTrait(first).getCurrentValue(), traitMap.getTrait(second).getCurrentValue());
  }

  private static int getTotalValue(TraitMap traitCollection, TraitType... types) {
    TraitTypeList list = new TraitTypeList();
    list.add(types);
    return getTotalValue(traitCollection, list);
  }


  private static int getTotalValue(TraitMap traitCollection, TraitTypeList types) {
    int sum = 0;
    for (TraitType type : types) {
      sum += traitCollection.getTrait(type).getCurrentValue();
    }
    return sum;
  }

  private static int getDodgeDvPool(TraitMap traitCollection) {
    int essence = traitCollection.getTrait(Essence).getCurrentValue();
    TraitTypeList typeList = new TraitTypeList();
    typeList.add(Dexterity);
    typeList.add("Dodge");
    int dvPool = getTotalValue(traitCollection, typeList);
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