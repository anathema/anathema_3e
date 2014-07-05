package net.sf.anathema.hero.traits.model;

public interface TraitRules {

  int UNEXPERIENCED = -1;

  int getAbsoluteMaximumValue();

  int getAbsoluteMinimumValue();

  int getCurrentMaximumValue(boolean modified);

  int getStartValue();

  boolean isReducible();

  TraitType getType();

  int getExperiencedValue(int creationValue, int demandedValue);

  int getCreationValue(int demandedValue);

  void setCapModifier(int modifier);

  boolean isRequiredFavored();
}