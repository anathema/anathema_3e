package net.sf.anathema.library.event;

public interface ObjectValueListener<T> {

  void valueChanged(T newValue);
}