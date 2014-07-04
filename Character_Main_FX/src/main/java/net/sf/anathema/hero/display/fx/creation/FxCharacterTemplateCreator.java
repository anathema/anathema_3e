package net.sf.anathema.hero.display.fx.creation;

import net.sf.anathema.hero.application.creation.CharacterCreationPageProperties;
import net.sf.anathema.hero.application.creation.CharacterCreationPresenter;
import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.creation.ICharacterItemCreationModel;
import net.sf.anathema.hero.application.creation.IItemOperator;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.fx.environment.DialogFactory;

public class FxCharacterTemplateCreator implements CharacterTemplateCreator {
  private Environment environment;
  private final DialogFactory factory;

  public FxCharacterTemplateCreator(DialogFactory factory) {
    this.factory = factory;
  }

  @Override
  public void createTemplate(IItemOperator operator, ICharacterItemCreationModel creationModel) {
    FxCharacterCreationView view = new FxCharacterCreationView(factory);
    CharacterCreationPageProperties properties = new CharacterCreationPageProperties(environment);
    new CharacterCreationPresenter(view, properties, creationModel, operator).initPresentation();
  }

  @Override
  public void useEnvironment(Environment environment) {
    this.environment = environment;
  }
}