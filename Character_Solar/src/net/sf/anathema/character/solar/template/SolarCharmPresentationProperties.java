package net.sf.anathema.character.solar.template;

import java.awt.Dimension;

import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;

public class SolarCharmPresentationProperties implements ICharmPresentationProperties {

  private static final String POLYGON = "95.0,4.0 103.0,10.0 159.0,10.0 155.0,16.0 178.0,16.0 178.0,32.0 184.0,38.0 178.0,44.0 178.0,60.0 155.0,60.0 159.0,66.0 103.0,66.0 95.0,72.0 87.0,66.0 31.0,66.0 35.0,60.0 12.0,60.0 12.0,44.0 6.0,38.0 12.0,32.0 12.0,16.0 35.0,16.0 31.0,10.0 87.0,10.0"; //$NON-NLS-1$

  public String getCharmFramePolygonString() {
    return POLYGON;
  }

  // private final Dimension SOLAR_INNER = new Dimension(138, 46);
  public Dimension getCharmDimension() {
    return new Dimension(190, 72);
  }

  public Dimension getGapDimension() {
    return new Dimension(25, 50);
  }
}
