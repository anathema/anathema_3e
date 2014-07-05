package net.sf.anathema.hero.dummy;

import net.sf.anathema.hero.individual.splat.HeroType;

public class DummyMundaneHeroType implements HeroType {

  @Override
  public boolean isExaltType() {
    return false;
  }

  @Override
  public boolean isEssenceUser() {
    return false;
  }

  @Override
  public String getId() {
    return "Dummy";
  }

  public boolean equals(Object other) {
    return other instanceof DummyMundaneHeroType;
  }
}