package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.model.type.ForCharacterType;
import net.sf.anathema.library.initialization.DoNotInstantiateAutomatically;

@DoNotInstantiateAutomatically
@ForCharacterType("Dummy")
public class DummyCharmPresentationProperties implements CharmPresentationProperties {
  @Override
  public boolean supportsCharacterType(CharacterType type) {
    return true;
  }

  @Override
  public RGBColor getColor() {
    return new RGBColor(0, 0, 0);
  }
}
