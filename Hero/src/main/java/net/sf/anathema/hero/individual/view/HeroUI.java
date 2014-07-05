package net.sf.anathema.hero.individual.view;

import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.resources.RelativePath;

public class HeroUI {

  public RelativePath getCharacterDescriptionTabIcon() {
    return new RelativePath("icons/TabDescription16.png");
  }

  public RelativePath getLinkIconPath() {
    return new RelativePath("icons/ButtonLink16b.png");
  }

  public RelativePath getRandomThresholdNameIconPath() {
    return new RelativePath("icons/ButtonRandomNameB16.png");
  }

  public RelativePath getSmallTypeIconPath(HeroType heroType) {
    return new RelativePath("icons/" + heroType.getId() + "Icon16.png");
  }

  public RelativePath getLargeTypeIconPath(HeroType heroType) {
    return new RelativePath("icons/" + heroType.getId() + "Icon100.png");
  }

  public RelativePath getMediumBallPath(HeroType heroType) {
    return new RelativePath("icons/Border" + heroType.getId() + "Button16.png");
  }

  public RelativePath getRandomRealmNameIconPath() {
    return new RelativePath("icons/ButtonRandomNameC16.png");
  }

  public RelativePath getCancelComboEditIconPath() {
    return new RelativePath("icons/ButtonUndo16.png");
  }

  public RelativePath getFinalizeIconPath() {
    return new RelativePath("icons/ButtonCheck16.png");
  }
}