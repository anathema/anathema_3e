package net.sf.anathema.library.model;

import net.sf.anathema.library.event.ChangeListener;
import org.jmock.example.announcer.Announcer;

public class IntegerModelImpl implements IntegerModel {

  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private int value;

  public IntegerModelImpl(int value) {
    this.value = value;
  }

  @Override
  public final int getValue() {
    return value;
  }

  @Override
  public final void setValue(int value) {
    if (this.value == value) {
      return;
    }
    this.value = value;
    fireValueChangedEvent();
  }

  @Override
  public final void addChangeListener(ChangeListener listener) {
    control.addListener(listener);
  }

  private void fireValueChangedEvent() {
    control.announce().changeOccurred();
  }
}