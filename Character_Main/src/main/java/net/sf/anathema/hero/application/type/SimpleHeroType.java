package net.sf.anathema.hero.application.type;

import net.sf.anathema.hero.individual.splat.HeroType;

public class SimpleHeroType implements HeroType {

  public String id;
  public boolean exalt;
  public boolean essenceUser;

  @Override
  public boolean isExaltType() {
    return exalt;
  }

  @Override
  public boolean isEssenceUser() {
    return essenceUser;
  }

  @Override
  public String getId() {
    return id;
  }
}