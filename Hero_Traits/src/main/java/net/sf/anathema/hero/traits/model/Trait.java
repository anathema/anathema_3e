package net.sf.anathema.hero.traits.model;

import net.sf.anathema.library.event.IntegerChangedListener;

public interface Trait {

  TraitType getType();

  int getCurrentValue();

  int getCreationValue();

  int getExperiencedValue();

  int getAbsoluteMinValue();

  boolean isLowerable();

  void resetCreationValue();

  void resetExperiencedValue();

  void setCreationValue(int value);

  void setExperiencedValue(int value);

  int getMaximalValue();

  void addCurrentValueListener(IntegerChangedListener listener);

  void removeCurrentValueListener(IntegerChangedListener listener);

  int getModifiedMaximalValue();

  int getUnmodifiedMaximalValue();

  void setUncheckedCreationValue(int value);

  void setUncheckedExperiencedValue(int value);

  void setCurrentValue(int value);

  int getMinimalValue();

  void resetCurrentValue();
}