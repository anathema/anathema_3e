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
    return specialtyConfiguration.getSpecialtiesContainer(traitType).getSubTraits();
  }

  public void addSpecialtyListChangeListener(final ChangeListener listener) {
    SpecialtiesModel config = SpecialtiesModelFetcher.fetch(hero);
    for (TraitType trait : config.getAllParentTraits()) {
      config.getSpecialtiesContainer(trait).addSubTraitListener(new ISpecialtyListener() {
        @Override
        public void subTraitValueChanged() {
        }

        @Override
        public void subTraitAdded(Specialty subTrait) {
          listener.changeOccurred();
        }

        @Override
        public void subTraitRemoved(Specialty subTrait) {
          listener.changeOccurred();
        }
      });
    }
  }
}
