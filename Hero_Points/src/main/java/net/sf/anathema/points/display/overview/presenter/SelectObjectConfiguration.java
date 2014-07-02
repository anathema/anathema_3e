package net.sf.anathema.points.display.overview.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.util.Identifier;

public class SelectObjectConfiguration<T> extends AbstractUIConfiguration<T> {
  private final Resources resources;
  private ValueI18n<T> valueI18n;

  public SelectObjectConfiguration(Resources resources, ValueI18n<T> valueI18n) {
    this.resources = resources;
    this.valueI18n = valueI18n;
  }

  @Override
  public String getLabel(T value) {
    if (isUnselected(value)) {
      return resources.getString("ComboBox.SelectLabel");
    }
    return valueI18n.getLabel(resources, value);
  }

  @Override
  public RelativePath getIconsRelativePath(T value) {
    if (isUnselected(value)) {
      return NO_ICON;
    }
    return getIconForObject(value);
  }

  @SuppressWarnings("UnusedParameters")
  protected RelativePath getIconForObject(T value) {
    return NO_ICON;
  }

  private boolean isUnselected(T value) {
    return value == null;
  }
}