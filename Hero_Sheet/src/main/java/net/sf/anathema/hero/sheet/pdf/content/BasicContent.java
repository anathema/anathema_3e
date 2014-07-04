package net.sf.anathema.hero.sheet.pdf.content;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.CharacterType;

public class BasicContent implements SubContent {

  private Hero hero;

  public BasicContent(Hero hero) {
    this.hero = hero;
  }

  public boolean isEssenceUser() {
    return getCharacterType().isEssenceUser();
  }

  private CharacterType getCharacterType() {
    return hero.getSplat().getTemplateType().getCharacterType();
  }

  public boolean hasTypeId(String id) {
    return getCharacterType().getId().equals(id);
  }

  @Override
  public boolean hasContent() {
    return true;
  }
}
