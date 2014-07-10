package net.sf.anathema.hero.application.creation;

import net.sf.anathema.platform.environment.Environment;

public interface CharacterTemplateCreator {

  void createTemplate(IItemOperator operator, ICharacterItemCreationModel creationModel);

  void useEnvironment(Environment environment);
}