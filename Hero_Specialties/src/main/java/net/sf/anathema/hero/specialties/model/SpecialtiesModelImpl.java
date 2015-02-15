package net.sf.anathema.hero.specialties.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.change.ChangeFlavor;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;

import org.jmock.example.announcer.Announcer;

import com.google.common.base.Strings;

public class SpecialtiesModelImpl implements SpecialtiesModel, HeroModel {

  private final List<Specialty> specialties = new ArrayList<>();
  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private final Announcer<ISpecialtyListener> specialtiesChangedListener = Announcer.to(ISpecialtyListener.class);
  private Hero hero;
  private String currentName;
  private TraitType currentType;

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
  }

  @Override
  public void initializeListening(final ChangeAnnouncer announcer) {
    specialtiesChangedListener.addListener(new ISpecialtyListener() {
      @Override
      public void specialtyAdded(Specialty subTrait) {
        announcer.announceChangeFlavor(ChangeFlavor.UNSPECIFIED);
      }

      @Override
      public void specialtyRemoved(Specialty subTrait) {
        announcer.announceChangeFlavor(ChangeFlavor.UNSPECIFIED);
      }
    });
  }

  @Override
  public Identifier getId() {
    return SpecialtiesModel.ID;
  }

  @Override
  public List<Specialty> getAllSpecialties() {
    return new ArrayList<>(specialties);
  }

  @Override
  public List<Specialty> getAllSpecialtiesOfType(TraitType type) {
    List<Specialty> traitSpecialties = getAllSpecialties();
    traitSpecialties.removeIf(specialty -> !specialty.getBasicTraitType().equals(type));
    return traitSpecialties;
  }

  @Override
  public boolean removeSpecialty(Specialty specialty) {
    if (specialty.isLearnedAtCreation() && isExperienced()) {
      return false;
    }
    boolean remove = specialties.remove(specialty);
    specialtiesChangedListener.announce().specialtyRemoved(specialty);
    return remove;
  }

  @Override
  public List<TraitType> getAllEligibleParentTraits() {
    List<TraitType> eligibleTypes = new ArrayList<>();
    for (Trait ability : AbilitiesModelFetcher.fetch(hero).getAll()) {
      if (isNewSpecialtyAllowed(ability)) {
        eligibleTypes.add(ability.getType());
      }
    }
    return eligibleTypes;
  }

  private boolean isNewSpecialtyAllowed(Trait ability) {
    return ability.getCurrentValue() > 0;
  }

  @Override
  public void setCurrentSpecialtyName(String newSpecialtyName) {
    this.currentName = newSpecialtyName;
    control.announce().changeOccurred();
  }

  @Override
  public void setCurrentTrait(TraitType newValue) {
    if (getAllEligibleParentTraits().contains(newValue)) {
      this.currentType = newValue;
      control.announce().changeOccurred();
    }
  }

  @Override
  public boolean commitSelection() {
    return addSpecialty(hero, currentType, currentName, !isExperienced()) != null;
  }

  public Specialty addSpecialty(Hero hero, TraitType trait, String name, boolean isCreationLearned) {
    if (!getAllEligibleParentTraits().contains(trait)) {
      return null;
    }
    Specialty specialty = new SpecialtyImpl(hero, trait, name, isCreationLearned);
    specialties.add(specialty);
    specialtiesChangedListener.announce().specialtyAdded(specialty);
    return specialty;
  }

  @Override
  public void clear() {
    currentName = null;
    currentType = null;
    control.announce().changeOccurred();
  }

  @Override
  public void addSelectionChangeListener(ChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public void addSpecialtiesChangedListener(ISpecialtyListener listener) {
    specialtiesChangedListener.addListener(listener);
  }

  @Override
  public TraitType getCurrentTrait() {
    return currentType;
  }

  @Override
  public String getCurrentName() {
    return currentName;
  }

  @Override
  public boolean isEntryComplete() {
    return !Strings.isNullOrEmpty(currentName) && currentType != null;
  }

  @Override
  public boolean isExperienced() {
    return ExperienceModelFetcher.fetch(hero).isExperienced();
  }
}