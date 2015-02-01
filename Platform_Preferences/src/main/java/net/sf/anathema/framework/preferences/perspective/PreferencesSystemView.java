package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.platform.fx.utility.UtilityPane;
import net.sf.anathema.platform.preferences.PreferenceView;

import java.util.ArrayList;
import java.util.Collection;

public class PreferencesSystemView {
  public final UtilityPane utilityPane = new UtilityPane();
  public final FxPreferencesNavigation preferencesNavigation;
  public final FxPreferencesView preferencesView;

  public PreferencesSystemView(ObjectFactory objectFactory) {
    Collection<PreferenceView> views = objectFactory.instantiateAllImplementers(PreferenceView.class);
    this.preferencesView = new FxPreferencesView();
    this.preferencesNavigation = new FxPreferencesNavigation(new ArrayList<>(views), preferencesView, null);
    utilityPane.setNavigationComponent(preferencesNavigation.getNode());
    utilityPane.setContentComponent(preferencesView.getNode());
  }
}