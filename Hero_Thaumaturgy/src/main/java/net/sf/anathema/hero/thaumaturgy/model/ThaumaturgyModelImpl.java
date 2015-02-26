package net.sf.anathema.hero.thaumaturgy.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.thaumaturgy.compiler.ThaumaturgyRitualCache;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.model.AbstractOptionalTraitModel;
import net.sf.anathema.library.model.NullCategory;

public class ThaumaturgyModelImpl extends AbstractOptionalTraitModel<NullCategory, ThaumaturgyRitual, KnownRitual>
	implements ThaumaturgyModel {

  protected ThaumaturgyModelImpl() {
		super(false);
	}

	private final List<ThaumaturgyProvider> providers = new ArrayList<>();

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
  	super.initialize(environment, hero);
    this.cache = environment.getDataSet(ThaumaturgyRitualCache.class);
  }
  
  @Override
	protected KnownRitual createKnownTrait(ThaumaturgyRitual option,
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
			return getFreeRituals() > 0;
		}
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
	
	private int getFreeRituals() {
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