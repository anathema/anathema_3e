package net.sf.anathema.hero.equipment;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.model.RemovableEntryListener;

import java.util.Collection;

public class SpecialtiesCollectionImpl {

  private final Hero hero;

  public SpecialtiesCollectionImpl(Hero hero) {
    this.hero = hero;
  }

  public Collection<Specialty> getSpecialties(TraitType traitType) {
    SpecialtiesModel specialtyConfiguration = SpecialtiesModelFetcher.fetch(hero);
    return specialtyConfiguration.getAllSpecialtiesOfType(traitType);
  }

  public void addSpecialtyListChangeListener(final ChangeListener listener) {
    SpecialtiesModelFetcher.fetch(hero).addModelChangeListener(new RemovableEntryListener<Specialty>() {

        @Override
        public void entryAdded(Specialty entry) {
          listener.changeOccurred();
        }

        @Override
        public void entryRemoved(Specialty entry) {
          listener.changeOccurred();
        }

        @Override
        public void entryAllowed(boolean complete) {
          // nothing to do
        }
      });
  }
}
