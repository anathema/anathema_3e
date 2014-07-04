package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.library.preferences.Preferences;
import net.sf.anathema.platform.preferences.PreferenceValue;

public class PageSizePreference {

  private Preferences preferences;

  public PageSizePreference(Preferences preferences) {
    this.preferences = preferences;
  }

  public PageSize getPageSize() {
    String value = preferences.getPreference(SheetPreferenceModel.KEY.key);
    if (value == Preferences.PREFERENCE_NOT_SET) {
      return PageSize.A4;
    }
    PreferenceValue preferenceValue = new PreferenceValue(value);
    return SheetPreferenceModel.calculatePageSize(preferenceValue);
  }
}