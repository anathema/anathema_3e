package net.sf.anathema.hero.flaws.model;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.flaws.compiler.FlawCache;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.model.OptionalEntryOptionSupplier;
import net.sf.anathema.library.model.property.AbstractOptionalPropertiesModel;

public class FlawsModelImpl extends AbstractOptionalPropertiesModel<FlawOption, Flaw>
	implements FlawsModel, HeroModel {

  public FlawsModelImpl() {
		super(false);
	}

	@Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    super.initialize(environment, hero);
  }

  @Override
  public Identifier getId() {
    return FlawsModel.ID;
  }
  
  @Override
	protected boolean isAllowedOption(FlawOption option) {
  	return !getEntries().stream().anyMatch(flaw -> flaw.getBaseOption().equals(option));
	}
  
  @Override
	public boolean isRemovalAllowed(Flaw entry) {
  	return true;
	}

	@Override
	protected OptionalEntryOptionSupplier<FlawOption> initOptionSupplier(HeroEnvironment environment) {
		return environment.getDataSet(FlawCache.class);
	}
	
	@Override
	public boolean isEntryAllowed() {
		return !(getSelectedEntryOption() instanceof NullFlawOption);
	}

	@Override
	protected Flaw createPossessedEntry(FlawOption option,
			String description, Hero hero) {
		return new FlawImpl(option, description, !isCharacterExperienced());
	}

	private boolean isCharacterExperienced() {
    return ExperienceModelFetcher.fetch(hero).isExperienced();
  }

	@Override
	protected FlawOption getNullOption() {
		return new NullFlawOption();
	}	
}