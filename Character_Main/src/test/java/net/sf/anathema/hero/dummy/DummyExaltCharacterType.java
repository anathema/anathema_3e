package net.sf.anathema.hero.dummy;

import net.sf.anathema.hero.individual.splat.CharacterType;

public class DummyExaltCharacterType implements CharacterType {

  @Override
  public boolean isExaltType() {
    return true;
  }

  @Override
  public boolean isEssenceUser() {
    return true;
  }

  @Override
  public String getId() {
    return "Dummy";
  }

  public boolean equals(Object other) {
    return other instanceof DummyExaltCharacterType;
  }
}