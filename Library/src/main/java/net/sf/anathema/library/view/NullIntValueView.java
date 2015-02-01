package net.sf.anathema.library.view;

import net.sf.anathema.library.event.IntegerChangedListener;

public class NullIntValueView implements IntValueView {

  @Override
  public void setValue(int newValue) {
  }

  @Override
  public void addIntValueChangedListener(IntegerChangedListener listener) {

  }

  @Override
  public void removeIntValueChangedListener(IntegerChangedListener listener) {

  }
}