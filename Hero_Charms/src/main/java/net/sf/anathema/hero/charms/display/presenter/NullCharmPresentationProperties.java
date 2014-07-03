package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import net.sf.anathema.hero.framework.type.CharacterType;

@DoNotInstantiateAutomatically
public class NullCharmPresentationProperties extends AbstractCharmPresentationProperties {
  @Override
  public boolean supportsCharacterType(CharacterType type) {
    return true;
  }
}