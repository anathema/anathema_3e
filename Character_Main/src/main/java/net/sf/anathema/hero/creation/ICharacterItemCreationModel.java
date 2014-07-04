package net.sf.anathema.hero.creation;

import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.library.event.ChangeListener;

public interface ICharacterItemCreationModel {

  void setCharacterType(CharacterType type);

  void setSelectedTemplate(HeroTemplate newValue);

  void addListener(ChangeListener listener);

  Iterable<CharacterType> getAvailableCharacterTypes();

  HeroTemplate[] getAvailableTemplates();

  HeroTemplate getSelectedTemplate();
}