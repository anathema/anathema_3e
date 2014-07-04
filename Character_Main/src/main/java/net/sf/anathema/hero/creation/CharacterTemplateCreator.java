package net.sf.anathema.hero.creation;

import net.sf.anathema.platform.environment.Environment;

public interface CharacterTemplateCreator {

  void createTemplate(final IItemOperator operator, final ICharacterItemCreationModel creationModel);

  void useEnvironment(Environment environment);
}