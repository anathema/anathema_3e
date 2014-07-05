package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.traits.model.state.TraitStateModel;
import net.sf.anathema.library.event.IntegerChangedListener;

public interface Trait extends ValuedTraitType {

  int getCreationValue();

  int getExperiencedValue();

  int getAbsoluteMinValue();

  boolean isLowerable();

  void resetCreationValue();

  void resetExperiencedValue();

  void setCreationValue(int value);

  void setExperiencedValue(int value);

  TraitStateModel getFavorization();

  int getMaximalValue();

  void addCreationPointListener(IntegerChangedListener listener);

  void removeCreationPointListener(IntegerChangedListener listener);

  void addCurrentValueListener(IntegerChangedListener listener);

  int getModifiedMaximalValue();

  int getUnmodifiedMaximalValue();

  void setUncheckedCreationValue(int value);

  void setUncheckedExperiencedValue(int value);

  void setCurrentValue(int value);

  int getMinimalValue();

  void resetCurrentValue();

  void setModifiedCreationRange(int newInitialValue, int newUpperValue);
}