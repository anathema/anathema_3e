package net.sf.anathema.hero.sheet.pdf.content;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.HeroType;

public class BasicContent implements SubContent {

  private Hero hero;

  public BasicContent(Hero hero) {
    this.hero = hero;
  }

  public boolean isEssenceUser() {
    return getCharacterType().isEssenceUser();
  }

  private HeroType getCharacterType() {
    return hero.getSplat().getTemplateType().getHeroType();
  }

  public boolean hasTypeId(String id) {
    return getCharacterType().getId().equals(id);
  }

  @Override
  public boolean hasContent() {
    return true;
  }
}
