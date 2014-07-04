package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.ObjectValueListener;
import net.sf.anathema.library.interaction.model.Command;

public interface SpecialtyCreationView {

  void addSelectionChangedListener(ObjectValueListener<TraitType> name);

  void addEditChangedListener(ObjectValueListener<String> name);

  void whenAddButtonIsClicked(Command command);

  void clear();

  void setButtonEnabled(boolean enabled);

  void setObjects(TraitType[] objects);

  void enterName(String currentName);

  void selectTrait(TraitType currentTrait);
}