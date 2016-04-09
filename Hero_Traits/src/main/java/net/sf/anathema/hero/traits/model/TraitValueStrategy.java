package net.sf.anathema.hero.traits.model;

import net.sf.anathema.library.event.IntegerChangedListener;
import net.sf.anathema.library.event.IntegerChangingListener;

import org.jmock.example.announcer.Announcer;

public interface TraitValueStrategy {

  int getMinimalValue(Trait trait);

  int getCurrentValue(Trait trait);

  void setValue(Trait trait, int value);

  void notifyOnCreationValueChange(int value, Announcer<IntegerChangedListener> currentValueControl);

  void notifyOnLearnedValueChange(int value, Announcer<IntegerChangedListener> currentValueControl);
  
  void notifyOnCreationValueChanging(int from, int to, Announcer<IntegerChangingListener> changingValueControl);

  void notifyOnLearnedValueChanging(int from, int to, Announcer<IntegerChangingListener> changingValueControl);

  void resetCurrentValue(Trait trait);
}