package net.sf.anathema.hero.equipment;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.specialties.model.ISpecialtyListener;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.ChangeListener;

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
    SpecialtiesModelFetcher.fetch(hero).addSpecialtiesChangedListener(new ISpecialtyListener() {

        @Override
        public void specialtyAdded(Specialty specialty) {
          listener.changeOccurred();
        }

        @Override
        public void specialtyRemoved(Specialty specialty) {
          listener.changeOccurred();
        }
      });
  }
}
