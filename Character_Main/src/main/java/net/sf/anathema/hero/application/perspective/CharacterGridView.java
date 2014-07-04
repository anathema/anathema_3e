package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;

public interface CharacterGridView {

  void addButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector);

  void selectButton(CharacterIdentifier identifier);

  void updateButton(CharacterButtonDto dto);

  CharacterTemplateCreator createNewCharacter();
}
