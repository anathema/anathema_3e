package net.sf.anathema.hero.template;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.CharacterType;

public class NativeCharacterType {

  public static CharacterType get(Hero hero){
    return hero.getSplat().getTemplateType().getCharacterType();
  }
}