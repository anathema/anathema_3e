package net.sf.anathema.hero.traits.display;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.resources.Resources;

public class TraitTypeInternationalizer {

  private final Resources resources;

  public TraitTypeInternationalizer(Resources resources) {
    this.resources = resources;
  }

  public String getScreenName(TraitType type) {
    return resources.getString(type.getId());
  }
}