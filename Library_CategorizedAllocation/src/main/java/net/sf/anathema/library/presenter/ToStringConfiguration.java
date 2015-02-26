package net.sf.anathema.library.presenter;

import net.sf.anathema.library.presenter.AbstractUIConfiguration;

public class ToStringConfiguration<T> extends AbstractUIConfiguration<T> {

  @Override
  protected String labelForExistingValue(T value) {
    return value.toString();
  }
}