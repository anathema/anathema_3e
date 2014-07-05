package net.sf.anathema.library.presenter;

import net.sf.anathema.library.resources.Resources;

public interface ValueI18n<T> {

  String getLabel(Resources resources, T object);
}
