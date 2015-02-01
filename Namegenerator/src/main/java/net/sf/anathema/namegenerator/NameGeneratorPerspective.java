package net.sf.anathema.namegenerator;

import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.namegenerator.exalted.ExaltedNameGeneratorModel;
import net.sf.anathema.namegenerator.presenter.NameGeneratorPresenter;
import net.sf.anathema.namegenerator.presenter.model.INameGeneratorModel;
import net.sf.anathema.namegenerator.presenter.view.NameGeneratorView;
import net.sf.anathema.namegenerator.view.FxNameGeneratorView;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.fx.perspective.Container;
import net.sf.anathema.platform.fx.perspective.UtilityPerspective;
import net.sf.anathema.platform.messaging.MessageCategory;
import net.sf.anathema.platform.utility.UtilityAutoCollector;
import net.sf.anathema.platform.utility.UtilityToggle;

@UtilityAutoCollector
@Weight(weight = 7000)
public class NameGeneratorPerspective implements UtilityPerspective {

  @Override
  public void configureToggle(UtilityToggle toggle) {
    toggle.setTooltip("ItemType.NameGenerator.PrintName");
    toggle.setIcon(new RelativePath("icons/NameGeneratorPerspective.png"));
  }

  @Override
  public void initContent(Container container, ApplicationModel applicationModel, Environment environment, UiEnvironment uiEnvironment) {
    NameGeneratorView view = new FxNameGeneratorView();
    INameGeneratorModel generatorModel = new ExaltedNameGeneratorModel();
    new NameGeneratorPresenter(environment, view, generatorModel).initPresentation();
    container.setContent(view.getNode());
  }

  @Override
  public MessageCategory getMessageCategory() {
    return new MessageCategory("NameGenerator");
  }
}
