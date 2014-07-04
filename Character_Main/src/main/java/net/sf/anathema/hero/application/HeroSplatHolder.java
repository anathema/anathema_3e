package net.sf.anathema.hero.application;

import net.sf.anathema.hero.individual.splat.HeroSplat;

public class HeroSplatHolder {

  private HeroSplat splat;

  public HeroSplat getSplat() {
    return splat;
  }

  public void setSplat(HeroSplat template) {
    this.splat = template;
  }

  public boolean isCurrentlySelected(HeroSplat newValue) {
    return newValue == splat;
  }
}