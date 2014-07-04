package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.library.initialization.DoNotInstantiateAutomatically;

@DoNotInstantiateAutomatically
public class NullCharmPresentationProperties extends AbstractCharmPresentationProperties {
  @Override
  public boolean supportsCharacterType(CharacterType type) {
    return true;
  }
}