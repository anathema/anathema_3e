package net.sf.anathema.hero.traits.model;

import net.sf.anathema.library.event.IntegerChangedListener;
import org.jmock.example.announcer.Announcer;

public interface TraitValueStrategy {

  int getMinimalValue(Trait trait);

  int getCurrentValue(Trait trait);

  void setValue(Trait trait, int value);

  void notifyOnCreationValueChange(int value, Announcer<IntegerChangedListener> currentValueControl);

  void notifyOnLearnedValueChange(int value, Announcer<IntegerChangedListener> currentValueControl);

  void resetCurrentValue(Trait trait);
}