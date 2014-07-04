package net.sf.anathema.library.event;

public interface ObjectChangedListener<T> {

  void valueChanged(T newValue);
}