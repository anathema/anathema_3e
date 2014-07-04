package net.sf.anathema.library.model;

import net.sf.anathema.library.event.BooleanChangedListener;
import org.jmock.example.announcer.Announcer;

public class BooleanValueModel {

  private final Announcer<BooleanChangedListener> control = Announcer.to(BooleanChangedListener.class);
  private boolean value;

  public BooleanValueModel(boolean value) {
    this.value = value;
  }

  public boolean getValue() {
    return value;
  }

  public void addChangeListener(BooleanChangedListener listener) {
    control.addListener(listener);
  }

  public void setValue(boolean value) {
    if (value == this.value) {
      return;
    }
    this.value = value;
    control.announce().valueChanged(value);
  }
}