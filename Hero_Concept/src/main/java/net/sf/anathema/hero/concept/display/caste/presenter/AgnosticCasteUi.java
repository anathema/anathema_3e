package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.environment.herotype.PresentationProperties;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.points.display.overview.presenter.SelectObjectConfiguration;

public class AgnosticCasteUi extends SelectObjectConfiguration<CasteType> {
  private final CasteUI casteUI;

  public AgnosticCasteUi(Resources resources, PresentationProperties properties) {
    super(resources, (resources1, object) -> {
      String key = "Caste." + object.getId();
      return resources1.getString(key);
    });
    this.casteUI = new CasteUI(properties);
  }

  @Override
  protected RelativePath getIconForObject(CasteType value) {
    return casteUI.getSmallCasteIconPath(value);
  }
}