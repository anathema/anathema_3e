package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.library.interaction.model.Tool;

public interface UpdatingHeroesGridView extends HeroesGridView {

  void selectButton(HeroIdentifier identifier);

  void updateButton(CharacterButtonDto dto);

  CharacterTemplateCreator createNewCharacter(Tool caller);
}