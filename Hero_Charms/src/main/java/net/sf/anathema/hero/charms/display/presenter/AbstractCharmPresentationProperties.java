package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.framework.ui.RGBColor;

public abstract class AbstractCharmPresentationProperties implements CharmPresentationProperties {

  private RGBColor color;

  protected AbstractCharmPresentationProperties() {
    this(RGBColor.White);
  }

  protected AbstractCharmPresentationProperties(RGBColor color) {
    this.color = color;
  }

  @Override
  public RGBColor getColor() {
    return color;
  }
}