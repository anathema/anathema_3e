package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;

public interface CharacterNameChangeListener {

  void nameChanged(CharacterIdentifier identifier, String newName);
}
