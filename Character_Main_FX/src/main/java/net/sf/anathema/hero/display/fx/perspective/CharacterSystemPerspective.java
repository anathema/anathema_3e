package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.environment.HeroEnvironmentFetcher;
import net.sf.anathema.hero.application.perspective.CharacterGridPresenter;
import net.sf.anathema.hero.application.perspective.CharacterMessaging;
import net.sf.anathema.hero.application.perspective.CharacterStackBridge;
import net.sf.anathema.hero.application.perspective.CharacterStackPresenter;
import net.sf.anathema.hero.application.perspective.CharacterSystemModel;
import net.sf.anathema.hero.application.perspective.InteractionPresenter;
import net.sf.anathema.hero.application.perspective.ShowOnSelect;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.fx.perspective.Container;
import net.sf.anathema.platform.fx.perspective.Perspective;
import net.sf.anathema.platform.messaging.MessageCategory;
import net.sf.anathema.platform.perspective.PerspectiveAutoCollector;
import net.sf.anathema.platform.perspective.PerspectiveToggle;

@PerspectiveAutoCollector
@Weight(weight = 1)
public class CharacterSystemPerspective implements Perspective {
  private final CharacterMessaging characterMessaging = new CharacterMessaging();

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon(new RelativePath("icons/King-icon.png"));
    toggle.setTooltip("CharacterSystem.Perspective.Name");
  }

  @Override
  public void initContent(Container container, ApplicationModel model, Environment environment, UiEnvironment uiEnvironment) {
    new HeroSystemInitializer(model, environment).initializeCharacterSystem();
    characterMessaging.setDelegate(model.getMessaging());
    HeroEnvironment heroEnvironment = HeroEnvironmentFetcher.fetch(model);
    CharacterSystemModel systemModel = new CharacterSystemModel(model);
    CharacterSystemView view = new CharacterSystemView(uiEnvironment);
    container.setContent(view.getNode());
    CharacterViewFactory viewFactory = new CharacterViewFactory(heroEnvironment);
    CharacterStackBridge bridge = new CharacterStackFxBridge(viewFactory, view.getStackView());
    CharacterStackPresenter stackPresenter = new CharacterStackPresenter(bridge, systemModel);
    ShowOnSelect showOnSelect = new ShowOnSelect(characterMessaging, stackPresenter);
    CharacterGridPresenter gridPresenter = new CharacterGridPresenter(systemModel, view.getGridView(), showOnSelect, environment);
    gridPresenter.initPresentation();
    new InteractionPresenter(systemModel, view.getInteractionView(), environment, view.getGridView(), showOnSelect, uiEnvironment).initPresentation();
  }

  @Override
  public MessageCategory getMessageCategory() {
    return characterMessaging.getActiveCategory();
  }
}