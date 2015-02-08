package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.environment.HeroEnvironmentFetcher;
import net.sf.anathema.hero.application.perspective.CharacterMessaging;
import net.sf.anathema.hero.application.perspective.EastInteractionPresenter;
import net.sf.anathema.hero.application.perspective.HeroPoolModel;
import net.sf.anathema.hero.application.perspective.HeroStackBridge;
import net.sf.anathema.hero.application.perspective.HeroStackPresenter;
import net.sf.anathema.hero.application.perspective.RecentHeroesPresenter;
import net.sf.anathema.hero.application.perspective.ShowOnSelect;
import net.sf.anathema.hero.display.fx.perspective.content.HeroStackFxBridge;
import net.sf.anathema.hero.display.fx.perspective.content.HeroViewFactory;
import net.sf.anathema.hero.display.fx.perspective.navigation.WestInteractionPresenter;
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
  private HeroesStanceView view;

  @Override
  public void initContent(Container container, ApplicationModel model, Environment environment, UiEnvironment uiEnvironment) {
    new HeroPoolInitializer(model, environment).initializeCharacterSystem();
    characterMessaging.setDelegate(model.getMessaging());
    HeroEnvironment heroEnvironment = HeroEnvironmentFetcher.fetch(model);
    HeroPoolModel heroSystem = new HeroPoolModel(model);
    heroSystem.collectAllExistingHeroes();
    this.view = new HeroesStanceView(uiEnvironment);
    container.setContent(view.getNode());
    HeroViewFactory viewFactory = new HeroViewFactory(heroEnvironment.getObjectFactory());
    HeroStackBridge bridge = new HeroStackFxBridge(viewFactory, view.getStackView());
    HeroStackPresenter stackPresenter = new HeroStackPresenter(bridge, heroSystem, heroEnvironment);
    ShowOnSelect showOnSelect = new ShowOnSelect(characterMessaging, stackPresenter);
    new RecentHeroesPresenter(heroSystem, view.getGridView(), showOnSelect, environment).initPresentation();
    new HeroRosterPresenter(heroSystem, view, showOnSelect, environment).initPresentation();
    new WestInteractionPresenter(heroSystem, view.getCenterInteractionView(), environment, view.getGridView(), showOnSelect).initPresentation();
    new EastInteractionPresenter(heroSystem, view.getBackInteractionView(), environment, uiEnvironment).initPresentation();
  }

  @Override
  public MessageCategory getMessageCategory() {
    return characterMessaging.getActiveCategory();
  }

  @Override
  public Tool createLeaveTool() {
    return view.createLeaveTool();
  }

  @Override
  public void configureEnterTool(Tool tool) {
    tool.setText("Heroes");
  }
}