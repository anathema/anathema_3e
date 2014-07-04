package net.sf.anathema.library.model;

import net.sf.anathema.library.event.ChangeListener;
import org.jmock.example.announcer.Announcer;

public abstract class AbstractChangeableModel implements Cloneable, IChangeableModel {

  private transient Announcer<ChangeListener> listeners = Announcer.to(ChangeListener.class);

  @SuppressWarnings("CloneDoesntDeclareCloneNotSupportedException")
  @Override
  protected Object clone() {
    try {
      AbstractChangeableModel clone = (AbstractChangeableModel) super.clone();
      clone.listeners = Announcer.to(ChangeListener.class);
      return clone;
    }
    catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    listeners.addListener(listener);
  }

  @Override
  public void removeChangeListener(ChangeListener listener) {
    listeners.removeListener(listener);
  }

  protected void fireChangeEvent() {
    listeners.announce().changeOccurred();
  }
}