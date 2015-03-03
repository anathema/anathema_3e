package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.thaumaturgy.advance.ThaumaturgyExperienceModel;
import net.sf.anathema.hero.thaumaturgy.compiler.ThaumaturgyRitualCache;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.model.NullCategory;
import net.sf.anathema.library.model.trait.AbstractOptionalTraitsModel;
import net.sf.anathema.points.model.PointModelFetcher;

import java.util.ArrayList;
import java.util.List;

public class ThaumaturgyModelImpl extends AbstractOptionalTraitsModel<NullCategory, ThaumaturgyRitual, KnownRitual>
	implements ThaumaturgyModel {

  public ThaumaturgyModelImpl() {
		super(false);
	}

	private final List<ThaumaturgyProvider> providers = new ArrayList<>();
	
	@Override
	public void initialize(HeroEnvironment environment, Hero hero) {
		super.initialize(environment, hero);
		
		PointModelFetcher.fetch(hero).addToExperienceOverview(new ThaumaturgyExperienceModel("Thaumaturgy", this));
	}

  @Override
  public Identifier getId() {
    return ID;
  }
  
  @Override
  public ThaumaturgyRitualCache initCache(HeroEnvironment environment) {
  	return environment.getDataSet(ThaumaturgyRitualCache.class);
  }
  
  @Override
	protected KnownRitual createPossessedEntry(ThaumaturgyRitual option,
			String description, Hero hero) {
		return new KnownRitualImpl(option, description, hero);
	}
  
  @Override
  protected boolean isAllowedOption(ThaumaturgyRitual option) {
  	return !knowsTrait(option);
  }

	@Override
	public boolean isEntryAllowed() {
		if (!hasThaumaturgy()) {
			return false;
		}
		if (!ExperienceModelFetcher.fetch(hero).isExperienced()) {
			return getFreeRitualPicks() > 0;
		}
		return true;
	}
	
	@Override
	public boolean isRemovalAllowed(KnownRitual trait) {
		return true;
	}
	
	private boolean hasThaumaturgy() {
		for (ThaumaturgyProvider provider : providers) {
			if (provider.grantsThaumaturgy()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int getFreeRitualPicks() {
		int total = 0;
		for (ThaumaturgyProvider provider : providers) {
			total += provider.numberOfRitualsProvided();
		}
		return total;
	}

	@Override
	public void addThaumaturgyProvider(ThaumaturgyProvider provider) {
		providers.add(provider);
	}
	
	@Override
	public ThaumaturgyRitual getNullOption() {
		return new NullRitualOption();
	}
}