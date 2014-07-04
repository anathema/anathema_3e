package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.preferences.persistence.PropertiesPreferencesPersister;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.fx.perspective.Container;
import net.sf.anathema.platform.fx.perspective.Perspective;
import net.sf.anathema.platform.perspective.PerspectiveAutoCollector;
import net.sf.anathema.platform.perspective.PerspectiveToggle;

@PerspectiveAutoCollector
@Weight(weight = 8000)
public class PreferencesPerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon(new RelativePath("icons/preferences.png"));
    toggle.setTooltip("Preferences.Perspective");
  }

  @Override
  public void initContent(Container container, ApplicationModel applicationModel, Environment environment, UiEnvironment uiEnvironment) {
    PreferencesSystemView view = new PreferencesSystemView(environment);
    container.setContent(view.perspectivePane.getNode());
    PreferencesModel model = new CollectingPreferencesModel(environment);
    PreferencesPersister persister = new PropertiesPreferencesPersister();
    PreferencesPresenter presenter = new PreferencesPresenter(environment, view.preferencesNavigation, model, persister, environment);
    presenter.initialize();
  }
}