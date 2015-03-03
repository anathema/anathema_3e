package net.sf.anathema.library.presenter;


public class ToStringConfiguration<T> extends AbstractUIConfiguration<T> {

  @Override
  protected String labelForExistingValue(T value) {
    return value.toString();
  }
}