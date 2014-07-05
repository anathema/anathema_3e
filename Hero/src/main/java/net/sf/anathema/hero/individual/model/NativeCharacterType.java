package net.sf.anathema.hero.individual.model;

import net.sf.anathema.hero.individual.splat.HeroType;

public class NativeCharacterType {

  public static HeroType get(Hero hero){
    return hero.getSplat().getTemplateType().getHeroType();
  }
}