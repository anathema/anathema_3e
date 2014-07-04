package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.platform.preferences.PreferenceModel;
import net.sf.anathema.platform.preferences.PreferencePto;

public interface PreferencesModel {
  PreferencePto serialize();

  void initializeFrom(PreferencePto pto);

  PreferenceModel find(Class modelClass);
}