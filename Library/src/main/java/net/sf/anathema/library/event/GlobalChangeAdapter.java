package net.sf.anathema.library.event;

public class GlobalChangeAdapter<T> implements ObjectChangedListener<T>, IntegerChangedListener, BooleanChangedListener {

  private final ChangeListener listener;

  public GlobalChangeAdapter(ChangeListener listener) {
    this.listener = listener;
  }

  @Override
  public void valueChanged(T newValue) {
    listener.changeOccurred();
  }

  @Override
  public void valueChanged(int newValue) {
    listener.changeOccurred();
  }

  @Override
  public void valueChanged(boolean newValue) {
    listener.changeOccurred();
  }
}