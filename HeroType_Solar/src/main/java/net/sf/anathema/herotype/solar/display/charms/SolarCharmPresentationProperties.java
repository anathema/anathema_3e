package net.sf.anathema.herotype.solar.display.charms;

import net.sf.anathema.hero.application.type.ForCharacterType;
import net.sf.anathema.hero.charms.display.presenter.AbstractCharmPresentationProperties;
import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.herotype.solar.model.SolarType;
import net.sf.anathema.library.presenter.RGBColor;

@ForCharacterType("Solar")
public class SolarCharmPresentationProperties extends AbstractCharmPresentationProperties {

  public SolarCharmPresentationProperties() {
    super(new RGBColor(247, 234, 130));
  }

  @Override
  public boolean supportsCharacterType(CharacterType type) {
    return type.getId().equals(SolarType.ID);
  }
}
