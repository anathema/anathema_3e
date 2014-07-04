package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.library.event.IntValueChangedListener;

public interface IIntValueModel {

  Integer getMinimum();

  Integer getMaximum();

  void setValue(int value);

  int getValue();

  void addIntValueChangeListener(IntValueChangedListener changeListener);
}