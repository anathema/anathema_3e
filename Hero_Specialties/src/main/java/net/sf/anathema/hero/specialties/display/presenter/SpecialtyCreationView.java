package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.interaction.model.Command;

public interface SpecialtyCreationView {

  void addSelectionChangedListener(ObjectChangedListener<TraitType> name);

  void addEditChangedListener(ObjectChangedListener<String> name);

  void whenAddButtonIsClicked(Command command);

  void clear();

  void setButtonEnabled(boolean enabled);

  void setObjects(TraitType[] objects);

  void enterName(String currentName);

  void selectTrait(TraitType currentTrait);
}