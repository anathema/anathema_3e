package net.sf.anathema.hero.points.display.overview;

public interface IAdditionalSpendingModel extends SpendingModel {

  int getAdditionalValue();

  int getAdditionalRestrictedAlotment();

  boolean isExtensionRequired();

  int getRequiredSize();
}