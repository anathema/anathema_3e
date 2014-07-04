package net.sf.anathema.points.display.overview.presenter;

import net.sf.anathema.library.resources.Resources;

public interface ValueI18n<T> {

  String getLabel(Resources resources, T object);
}
