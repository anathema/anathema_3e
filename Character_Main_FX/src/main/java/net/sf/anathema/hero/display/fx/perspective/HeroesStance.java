package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.environment.HeroEnvironmentFetcher;
import net.sf.anathema.hero.application.perspective.BackInteractionPresenter;
import net.sf.anathema.hero.application.perspective.CharacterMessaging;
import net.sf.anathema.hero.application.perspective.HeroPoolModel;
import net.sf.anathema.hero.application.perspective.HeroPoolPresenter;
import net.sf.anathema.hero.application.perspective.HeroStackBridge;
import net.sf.anathema.hero.application.perspective.HeroStackPresenter;
import net.sf.anathema.hero.application.perspective.ShowOnSelect;
import net.sf.anathema.hero.display.fx.perspective.content.HeroStackFxBridge;
import net.sf.anathema.hero.display.fx.perspective.content.HeroViewFactory;
import net.sf.anathema.hero.display.fx.perspective.navigation.FrontInteractionPresenter;
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
    new HeroPoolInitializer(model, environment).initializeCharacterSystem();
    characterMessaging.setDelegate(model.getMessaging());
    HeroEnvironment heroEnvironment = HeroEnvironmentFetcher.fetch(model);
    HeroPoolModel heroSystem = new HeroPoolModel(model);
    HeroesStanceView view = new HeroesStanceView(uiEnvironment);
    container.setContent(view.getNode());
    HeroViewFactory viewFactory = new HeroViewFactory(heroEnvironment);
    HeroStackBridge bridge = new HeroStackFxBridge(viewFactory, view.getStackView());
    HeroStackPresenter stackPresenter = new HeroStackPresenter(bridge, heroSystem);
    ShowOnSelect showOnSelect = new ShowOnSelect(characterMessaging, stackPresenter);
    HeroPoolPresenter systemPresenter = new HeroPoolPresenter(heroSystem, view.getGridView(), showOnSelect, environment);
    systemPresenter.initPresentation();
    this.stanceView = view;
    new FrontInteractionPresenter(heroSystem, view.getFrontInteractionView(), environment, view.getGridView(), showOnSelect).initPresentation();
    new BackInteractionPresenter(heroSystem, view.getBackInteractionView(), environment, uiEnvironment).initPresentation();
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