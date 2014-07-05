package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.library.preferences.Preferences;
import net.sf.anathema.platform.preferences.PreferenceValue;

public class PageSizePreference {

  private HeroEnvironment environment;

  public PageSizePreference(HeroEnvironment environment) {
    this.environment = environment;
  }

  // todo
  public PageSize getPageSize() {
    String value = environment.getPreference(SheetPreferenceModel.KEY.key);
    if (value == Preferences.PREFERENCE_NOT_SET) {
      return PageSize.A4;
    }
    PreferenceValue preferenceValue = new PreferenceValue(value);
    return SheetPreferenceModel.calculatePageSize(preferenceValue);
  }
}