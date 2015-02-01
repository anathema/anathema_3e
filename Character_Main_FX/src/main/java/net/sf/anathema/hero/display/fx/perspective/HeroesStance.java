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
    CharacterSystemModel systemModel = new CharacterSystemModel(model);
    HeroesStanceView view = new HeroesStanceView(uiEnvironment);
    container.setContent(view.getNode());
    CharacterViewFactory viewFactory = new CharacterViewFactory(heroEnvironment);
    CharacterStackBridge bridge = new CharacterStackFxBridge(viewFactory, view.getStackView());
    CharacterStackPresenter stackPresenter = new CharacterStackPresenter(bridge, systemModel);
    ShowOnSelect showOnSelect = new ShowOnSelect(characterMessaging, stackPresenter);
    CharacterGridPresenter gridPresenter = new CharacterGridPresenter(systemModel, view.getGridView(), showOnSelect, environment);
    gridPresenter.initPresentation();
    this.stanceView = view;
    new InteractionPresenter(systemModel, view.getInteractionView(), environment, view.getGridView(), showOnSelect, uiEnvironment).initPresentation();
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