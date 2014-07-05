package net.sf.anathema.hero.dummy;

import net.sf.anathema.hero.individual.splat.HeroType;

public class DummyHeroType implements HeroType {

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
    return other instanceof DummyHeroType;
  }
}