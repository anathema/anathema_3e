package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.hero.specialties.display.view.SpecialtyView;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;

public interface SpecialtiesConfigurationView {

  SpecialtyView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon);

  SpecialtyCreationView addSpecialtyCreationView(AgnosticUIConfiguration<TraitType> configuration, RelativePath addIcon);
}