package net.sf.anathema.cascades.module;

import javafx.scene.Node;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.cascades.presenter.CharmCascadesPresenterImpl;
import net.sf.anathema.cascades.presenter.CharmTreeMap;
import net.sf.anathema.hero.application.environment.HeroEnvironmentFetcher;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.display.view.FxCharmView;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.fx.perspective.Container;
import net.sf.anathema.platform.fx.perspective.UtilityPerspective;
import net.sf.anathema.platform.messaging.MessageCategory;
import net.sf.anathema.platform.utility.UtilityAutoCollector;
import net.sf.anathema.platform.utility.UtilityToggle;

import org.tbee.javafx.scene.layout.MigPane;

@UtilityAutoCollector
@Weight(weight = 6000)
public class CharmCascadePerspective implements UtilityPerspective {
  @Override
  public void configureToggle(UtilityToggle toggle) {
    toggle.setIcon(new RelativePath("icons/toolbar/TaskBarCharms24.png"));
    toggle.setTooltip("ItemType.CharmCascades.PrintName");
  }

  @Override
  public void initContent(Container container, ApplicationModel applicationModel, Environment environment, UiEnvironment uiEnvironment) {
    HeroEnvironment heroEnvironment = HeroEnvironmentFetcher.fetch(applicationModel);
    MagicDescriptionProvider magicDescriptionProvider = getCharmDescriptionProvider(heroEnvironment);
    FxCharmView cascadeView = new FxCharmView();
    new CharmCascadesPresenterImpl(environment, heroEnvironment, cascadeView, magicDescriptionProvider,
            new CharmTreeMap()).initPresentation();
    MigPane content = createContentPane(cascadeView);
    container.setContent(content);
  }

  @Override
  public MessageCategory getMessageCategory() {
    return new MessageCategory("Cascades");
  }

  private MigPane createContentPane(FxCharmView cascadeView) {
    Node node = cascadeView.getNode();
    MigPane content = new MigPane(new LC().fill());
    content.add(node, new CC().grow().push());
    return content;
  }

  private MagicDescriptionProvider getCharmDescriptionProvider(HeroEnvironment environment) {
    return CharmDescriptionProviderExtractor.CreateFor(environment);
  }
}