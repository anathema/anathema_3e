package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.hero.display.fx.dot.ExtensibleDotView;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;

public interface SpecialtiesConfigurationView {

  ExtensibleDotView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon, int value, int maxValue);

  SpecialtyCreationView addSpecialtyCreationView(AgnosticUIConfiguration<TraitType> configuration, RelativePath addIcon);
}