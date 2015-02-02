package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;

public interface CharacterNameChangeListener {

  void nameChanged(HeroIdentifier identifier, String newName);
}
