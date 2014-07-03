package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.template.PresentationProperties;
import net.sf.anathema.lib.file.RelativePath;

public class CasteUI {

  private final PresentationProperties properties;

  public CasteUI(PresentationProperties properties) {
    this.properties = properties;
  }

  public RelativePath getSmallCasteIconPath(CasteType type) {
    return properties.getSmallCasteIconResource(type.getId());
  }
}