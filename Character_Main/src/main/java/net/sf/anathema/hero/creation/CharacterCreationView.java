package net.sf.anathema.hero.creation;

import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.VetoableObjectSelectionView;

public interface CharacterCreationView {

  ToggleButtonPanel addToggleButtonPanel();

  VetoableObjectSelectionView<HeroTemplate> addObjectSelectionList();

  void show();

  void close();

  Tool addButton();

  void setTitle(String title);
}