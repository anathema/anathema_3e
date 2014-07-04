package net.sf.anathema.library.model;

import net.sf.anathema.library.event.ChangeListener;
import org.jmock.example.announcer.Announcer;

public class BooleanModel implements IModifiableBooleanModel, IChangeableModel {

  private final Announcer<ChangeListener> listeners = Announcer.to(ChangeListener.class);
  private boolean value;

  public BooleanModel() {
    this(false);
  }

  public BooleanModel(boolean value) {
    this.value = value;
  }

  @Override
  public boolean getValue() {
    return value;
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    listeners.addListener(listener);
  }

  @Override
  public void removeChangeListener(ChangeListener listener) {
    listeners.removeListener(listener);
  }

  @Override
  public void setValue(boolean selected) {
    if (this.value == selected) {
      return;
    }
    this.value = selected;
    listeners.announce().changeOccurred();
  }

  @Override
  public String toString() {
    return "BooleanModel: " + value;
  }

}