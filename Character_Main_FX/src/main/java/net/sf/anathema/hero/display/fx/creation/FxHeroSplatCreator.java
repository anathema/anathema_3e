package net.sf.anathema.hero.display.fx.creation;

import javafx.scene.Node;

import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.creation.HeroCreationPageProperties;
import net.sf.anathema.hero.application.creation.HeroCreationPresenter;
import net.sf.anathema.hero.application.creation.ICharacterItemCreationModel;
import net.sf.anathema.hero.application.creation.IItemOperator;
import net.sf.anathema.platform.environment.Environment;

public class FxHeroSplatCreator implements CharacterTemplateCreator {
  private Environment environment;
  private Node parent;

  public FxHeroSplatCreator(Node parent) {
    this.parent = parent;
  }

  @Override
  public void createTemplate(IItemOperator operator, ICharacterItemCreationModel creationModel) {
    FxHeroCreationView view = new FxHeroCreationView(parent);
    HeroCreationPageProperties properties = new HeroCreationPageProperties(environment);
    new HeroCreationPresenter(view, properties, creationModel, operator).initPresentation();
  }

  @Override
  public void useEnvironment(Environment environment) {
    this.environment = environment;
  }
}