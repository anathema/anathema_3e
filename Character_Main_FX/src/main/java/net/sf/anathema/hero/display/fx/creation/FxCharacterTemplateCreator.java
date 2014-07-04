package net.sf.anathema.hero.display.fx.creation;

import net.sf.anathema.framework.environment.fx.DialogFactory;
import net.sf.anathema.hero.creation.CharacterCreationPageProperties;
import net.sf.anathema.hero.creation.CharacterCreationPresenter;
import net.sf.anathema.hero.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;
import net.sf.anathema.hero.creation.IItemOperator;
import net.sf.anathema.platform.environment.Environment;

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