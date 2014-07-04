package net.sf.anathema.hero.creation;

import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.VetoableObjectSelectionView;

public interface CharacterCreationView {

  ToggleButtonPanel addToggleButtonPanel();

  VetoableObjectSelectionView<HeroSplat> addObjectSelectionList();

  void show();

  void close();

  Tool addButton();

  void setTitle(String title);
}