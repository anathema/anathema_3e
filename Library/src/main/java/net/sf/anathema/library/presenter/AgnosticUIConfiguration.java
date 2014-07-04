package net.sf.anathema.library.presenter;

import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.tooltip.ConfigurableTooltip;

public interface AgnosticUIConfiguration<T> {
  RelativePath NO_ICON = null;
  String NO_LABEL= null;

  RelativePath getIconsRelativePath(T value);

  String getLabel(T value);

  void configureTooltip(T item, ConfigurableTooltip configurableTooltip);
}