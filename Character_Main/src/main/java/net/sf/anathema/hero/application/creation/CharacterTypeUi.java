package net.sf.anathema.hero.application.creation;

import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.resources.Resources;

public class CharacterTypeUi {

  private Resources resources;

  public CharacterTypeUi(Resources resources) {
    this.resources = resources;
  }

  public String getLabel(HeroType type) {
    return resources.getString("CharacterGenerator.NewCharacter." + type.getId() + ".Name");
  }
}
