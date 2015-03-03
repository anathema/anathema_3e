package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.model.OptionalEntryCache;
import net.sf.anathema.library.model.property.AbstractOptionalPropertiesModel;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SpecialtiesModelImpl extends AbstractOptionalPropertiesModel<SpecialtyType, Specialty>
	implements SpecialtiesModel, HeroModel {

  public SpecialtiesModelImpl() {
		super(false);
	}

	@Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    super.initialize(environment, hero);
  }

  @Override
  public Identifier getId() {
    return SpecialtiesModel.ID;
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
	protected OptionalEntryCache<SpecialtyType> initCache(
			HeroEnvironment environment) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isEntryAllowed() {
		return hasDescription() && !(getSelectedEntryOption() instanceof NullSpecialtyOption);
	}

	@Override
	protected Specialty createPossessedEntry(SpecialtyType option,
			String description, Hero hero) {
		return new SpecialtyImpl(option, description, isCharacterExperienced());
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
    return this.getAllEntryOptions().stream().map(option -> option.getTraitType()).collect(toList());
  }

	
}