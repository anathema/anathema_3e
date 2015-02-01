package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.preferences.persistence.PropertiesPreferencesPersister;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.fx.utility.Container;
import net.sf.anathema.platform.fx.utility.UtilityPerspective;
import net.sf.anathema.platform.messaging.MessageCategory;
import net.sf.anathema.platform.utility.UtilityAutoCollector;
import net.sf.anathema.platform.utility.UtilityToggle;

@UtilityAutoCollector
@Weight(weight = 8000)
public class PreferencesPerspective implements UtilityPerspective {
  @Override
  public void configureToggle(UtilityToggle toggle) {
    toggle.setIcon(new RelativePath("icons/preferences.png"));
    toggle.setTooltip("Preferences.Perspective");
  }

  @Override
  public void initContent(Container container, ApplicationModel applicationModel, Environment environment, UiEnvironment uiEnvironment) {
    PreferencesSystemView view = new PreferencesSystemView(environment.getObjectFactory());
    container.setContent(view.utilityPane.getNode());
    PreferencesModel model = new CollectingPreferencesModel(environment.getObjectFactory());
    PreferencesPersister persister = new PropertiesPreferencesPersister();
    PreferencesPresenter presenter = new PreferencesPresenter(environment, view.preferencesNavigation, model, persister, environment.getObjectFactory());
    presenter.initialize();
  }

  @Override
  public MessageCategory getMessageCategory() {
    return new MessageCategory("Preferences");
  }
}