package net.sf.anathema.hero.merits.model;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.health.model.HealthModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.compiler.MeritCache;
import net.sf.anathema.hero.merits.model.mechanics.MeritHealthProvider;
import net.sf.anathema.hero.merits.model.mechanics.MeritThaumaturgyProvider;
import net.sf.anathema.hero.merits.model.mechanics.MeritUnarmedModificationProvider;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyModelFetcher;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.model.AbstractOptionalTraitModel;
import net.sf.anathema.library.model.OptionalTraitReference;

public class MeritsModelImpl extends AbstractOptionalTraitModel<MeritCategory, MeritOption, Merit>
	implements MeritsModel {

  protected MeritsModelImpl() {
		super(true);
	}

	@Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
  	super.initialize(environment, hero);
    MeritHealthProvider healthProvider = new MeritHealthProvider(this);
    MeritUnarmedModificationProvider unarmedProvider = new MeritUnarmedModificationProvider(this);
    MeritThaumaturgyProvider thaumaturgyProvider = new MeritThaumaturgyProvider(this);
    HealthModelFetcher.fetch(hero).addHealthLevelProvider(healthProvider);
    HealthModelFetcher.fetch(hero).addPainToleranceProvider(healthProvider);
    HealthModelFetcher.fetch(hero).addHealingTypeProvider(healthProvider);
    EquipmentModelFetcher.fetch(hero).addUnarmedModification(unarmedProvider);
    ThaumaturgyModelFetcher.fetch(hero).addThaumaturgyProvider(thaumaturgyProvider);
  }
  
  @Override
	protected MeritCache initCache(HeroEnvironment environment) {
		return environment.getDataSet(MeritCache.class);
	}

  public List<Trait> getContingentTraits() {
    List<Trait> traits = new ArrayList<>();
    TraitTypeFinder typeFinder = new TraitTypeFinder();
    TraitModel traitModel = TraitModelFetcher.fetch(hero);
    for (MeritOption merit : cache.getAllOptions()) {
      for (String typeLabel : merit.getContingentTraitTypes()) {
        Trait trait = traitModel.getTrait(typeFinder.getTrait(typeLabel));
        if (!traits.contains(trait)) {
          traits.add(trait);
        }
      }
    }
    return traits;
  }

  @Override
  public boolean hasMeritsMatchingReference(OptionalTraitReference reference) {
    return !getMeritsMatchingReference(reference).isEmpty();
  }

  @Override
  public List<Merit> getMeritsMatchingReference(OptionalTraitReference reference) {
    Predicate<Merit> referencedMerits = merit -> merit.getBaseOption().isReferencedBy(reference);
    return getEntries().stream().filter(referencedMerits).collect(toList());
  }
  
  @Override
	protected boolean isAllowedOption(MeritOption option) {
		return option.isHeroEligible(hero) && (option.allowsRepurchase() || !knowsTrait(option));
	}

  @Override
  public boolean isEntryAllowed() {
    return isAllowedOption(getSelectedTraitOption());
  }

  private boolean isCharacterExperienced() {
    return ExperienceModelFetcher.fetch(hero).isExperienced();
  }

	@Override
	protected Merit createKnownTrait(MeritOption option, String description,
			Hero hero) {
		return new MeritImpl(option, description, hero, isCharacterExperienced());
	}

	@Override
	public List<MeritCategory> getAvailableCategories() {
		return Arrays.asList(MeritCategory.values());
	}

	@Override
	protected MeritOption getNullOption() {
		return new NullMeritOption();
	}
}