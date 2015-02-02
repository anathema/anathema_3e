package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.environment.HeroEnvironmentFetcher;
import net.sf.anathema.hero.application.perspective.CharacterMessaging;
import net.sf.anathema.hero.application.perspective.HeroStackBridge;
import net.sf.anathema.hero.application.perspective.HeroStackPresenter;
import net.sf.anathema.hero.application.perspective.HeroSystemModel;
import net.sf.anathema.hero.application.perspective.HeroSystemPresenter;
import net.sf.anathema.hero.application.perspective.InteractionPresenter;
import net.sf.anathema.hero.application.perspective.ShowOnSelect;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.fx.perspective.Container;
import net.sf.anathema.platform.fx.perspective.Stance;
import net.sf.anathema.platform.messaging.MessageCategory;
import net.sf.anathema.platform.stance.StanceAutoCollector;

@StanceAutoCollector
@Weight(weight = 1)
public class HeroesStance implements Stance {
  private final CharacterMessaging characterMessaging = new CharacterMessaging();
  private HeroesStanceView stanceView;

  @Override
  public void initContent(Container container, ApplicationModel model, Environment environment, UiEnvironment uiEnvironment) {
    new HeroSystemInitializer(model, environment).initializeCharacterSystem();
    characterMessaging.setDelegate(model.getMessaging());
    HeroEnvironment heroEnvironment = HeroEnvironmentFetcher.fetch(model);
    HeroSystemModel heroSystem = new HeroSystemModel(model);
    HeroesStanceView view = new HeroesStanceView(uiEnvironment);
    container.setContent(view.getNode());
    HeroViewFactory viewFactory = new HeroViewFactory(heroEnvironment);
    HeroStackBridge bridge = new HeroStackFxBridge(viewFactory, view.getStackView());
    HeroStackPresenter stackPresenter = new HeroStackPresenter(bridge, heroSystem);
    ShowOnSelect showOnSelect = new ShowOnSelect(characterMessaging, stackPresenter);
    HeroSystemPresenter systemPresenter = new HeroSystemPresenter(heroSystem, view.getGridView(), showOnSelect, environment);
    systemPresenter.initPresentation();
    this.stanceView = view;
    new InteractionPresenter(heroSystem, view.getInteractionView(), environment, view.getGridView(), showOnSelect, uiEnvironment).initPresentation();
  }

  @Override
  public MessageCategory getMessageCategory() {
    return characterMessaging.getActiveCategory();
  }

  @Override
  public Tool createLeaveTool() {
    return stanceView.createLeaveTool();
  }

  @Override
  public void configureEnterTool(Tool tool) {
    tool.setText("Heroes");
  }
}