package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.hero.environment.herotype.ForCharacterType;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.initialization.DoNotInstantiateAutomatically;
import net.sf.anathema.library.presenter.RGBColor;

@DoNotInstantiateAutomatically
@ForCharacterType("Dummy")
public class DummyCharmPresentationProperties implements CharmPresentationProperties {
  @Override
  public boolean supportsCharacterType(HeroType type) {
    return true;
  }

  @Override
  public RGBColor getColor() {
    return new RGBColor(0, 0, 0);
  }
}
