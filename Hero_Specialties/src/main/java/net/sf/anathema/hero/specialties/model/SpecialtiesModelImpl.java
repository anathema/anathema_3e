package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.display.TraitTypeInternationalizer;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.model.OptionalEntryOptionSupplier;
import net.sf.anathema.library.model.property.AbstractOptionalPropertiesModel;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SpecialtiesModelImpl extends AbstractOptionalPropertiesModel<SpecialtyType, Specialty>
        implements SpecialtiesModel, HeroModel {

  private TraitTypeInternationalizer i18;
  private List<TraitType> abilityTypes;

  public SpecialtiesModelImpl() {
    super(false);
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    super.initialize(environment, hero);
    i18 = new TraitTypeInternationalizer(environment.getResources());
    abilityTypes = AbilitiesModelFetcher.fetch(hero).getAllAbilityTypes();
  }

  @Override
  public Identifier getId() {
    return SpecialtiesModel.ID;
  }

  public List<Trait> getContingentTraits() {
    TraitModel traits = TraitModelFetcher.fetch(hero);
    return optionSupplier.getAllOptions().stream()
            .map(option -> traits.getTrait(option.getTraitType()))
            .collect(toList());
  }

  @Override
  protected boolean isAllowedOption(SpecialtyType option) {
    return AbilitiesModelFetcher.fetch(hero).getTrait(option.getTraitType())
            .getCurrentValue() > 0;
  }

  @Override
  public boolean isRemovalAllowed(Specialty entry) {
    return !isCharacterExperienced() || !entry.isLearnedAtCreation();
  }

  @Override
  protected OptionalEntryOptionSupplier<SpecialtyType> initOptionSupplier(HeroEnvironment environment) {
    return new AbilityOptionSupplier(i18, abilityTypes);
  }

  @Override
  public boolean isEntryAllowed() {
    return hasDescription() && !(getSelectedEntryOption() instanceof NullSpecialtyOption);
  }

  @Override
  protected Specialty createPossessedEntry(SpecialtyType option,
                                           String description, Hero hero) {
    return new SpecialtyImpl(option, description, !isCharacterExperienced());
  }

  private boolean isCharacterExperienced() {
    return ExperienceModelFetcher.fetch(hero).isExperienced();
  }

  @Override
  protected SpecialtyType getNullOption() {
    return new NullSpecialtyOption();
  }

  @Override
  public List<Specialty> getAllSpecialtiesOfType(TraitType type) {
    return getEntries().stream().filter(specialty -> specialty.getBasicTraitType().equals(type)).collect(toList());
  }

  @Override
  public List<TraitType> getAllEligibleParentTraits() {
    return this.getCurrentEntryOptions().stream().map(SpecialtyType::getTraitType).collect(toList());
  }


}