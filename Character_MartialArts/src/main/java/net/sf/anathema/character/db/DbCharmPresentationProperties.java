package net.sf.anathema.character.db;

import net.sf.anathema.hero.charms.display.presenter.AbstractCharmPresentationProperties;
import net.sf.anathema.hero.environment.herotype.ForCharacterType;
import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.library.presenter.RGBColor;

@ForCharacterType("Dragon-Blooded")
public class DbCharmPresentationProperties extends AbstractCharmPresentationProperties {

  public static final String ID = "Dragon-Blooded";

  public DbCharmPresentationProperties() {
    super(new RGBColor(139, 0, 0));
  }

  @Override
  public boolean supportsCharacterType(CharacterType type) {
    return type.getId().equals(ID);
  }
}
