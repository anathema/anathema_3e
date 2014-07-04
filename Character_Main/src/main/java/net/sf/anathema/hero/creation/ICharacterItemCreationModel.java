package net.sf.anathema.hero.creation;

import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.event.ChangeListener;

public interface ICharacterItemCreationModel {

  void setCharacterType(CharacterType type);

  void setSelectedTemplate(HeroSplat newValue);

  void addListener(ChangeListener listener);

  Iterable<CharacterType> getAvailableCharacterTypes();

  HeroSplat[] getAvailableTemplates();

  HeroSplat getSelectedTemplate();
}