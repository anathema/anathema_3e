package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.platform.preferences.PreferenceView;

public interface PreferencesNavigation {
  Tool addTool();

  PreferenceView addSection(String title, Class viewClass);
}