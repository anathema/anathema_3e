package net.sf.anathema.points.display.overview.presenter;

import net.sf.anathema.framework.environment.Resources;

public interface ValueI18n<T> {

  String getLabel(Resources resources, T object);
}
