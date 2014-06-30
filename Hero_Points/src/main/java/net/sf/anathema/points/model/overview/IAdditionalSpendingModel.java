package net.sf.anathema.points.model.overview;

public interface IAdditionalSpendingModel extends SpendingModel {

  int getAdditionalValue();

  int getAdditionalRestrictedAlotment();

  boolean isExtensionRequired();

  int getRequiredSize();
}