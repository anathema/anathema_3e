package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.traits.model.state.RequiredTraitState;
import net.sf.anathema.hero.traits.model.state.RequiredFavored;
import net.sf.anathema.hero.traits.model.state.NoRequiredState;

public interface TraitRules {

  int UNEXPERIENCED = -1;

  int getAbsoluteMaximumValue();

  int getAbsoluteMinimumValue();

  int getCurrentMinimumValue();

  int getCurrentMaximumValue(boolean modified);

  int getStartValue();

  boolean isReducible();

  TraitType getType();

  int getExperiencedValue(int creationValue, int demandedValue);

  int getCreationValue(int demandedValue);

  void setCapModifier(int modifier);

  boolean isRequiredFavored();
}