package net.sf.anathema.hero.display.fx.creation;

import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.creation.HeroCreationPageProperties;
import net.sf.anathema.hero.application.creation.HeroCreationPresenter;
import net.sf.anathema.hero.application.creation.ICharacterItemCreationModel;
import net.sf.anathema.hero.application.creation.IItemOperator;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.fx.environment.DialogFactory;

public class FxHeroSplatCreator implements CharacterTemplateCreator {
  private Environment environment;
  private final DialogFactory factory;

  public FxHeroSplatCreator(DialogFactory factory) {
    this.factory = factory;
  }

  @Override
  public void createTemplate(IItemOperator operator, ICharacterItemCreationModel creationModel) {
    FxHeroCreationView view = new FxHeroCreationView(factory);
    HeroCreationPageProperties properties = new HeroCreationPageProperties(environment);
    new HeroCreationPresenter(view, properties, creationModel, operator).initPresentation();
  }

  @Override
  public void useEnvironment(Environment environment) {
    this.environment = environment;
  }
}