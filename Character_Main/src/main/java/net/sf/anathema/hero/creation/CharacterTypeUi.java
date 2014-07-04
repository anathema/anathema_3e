package net.sf.anathema.hero.creation;

import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.library.resources.Resources;

public class CharacterTypeUi {

  private Resources resources;

  public CharacterTypeUi(Resources resources) {
    this.resources = resources;
  }

  public String getLabel(CharacterType type) {
    return resources.getString("CharacterGenerator.NewCharacter." + type.getId() + ".Name");
  }
}
