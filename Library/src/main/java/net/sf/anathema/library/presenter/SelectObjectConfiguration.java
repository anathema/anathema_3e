package net.sf.anathema.library.presenter;

import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;

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