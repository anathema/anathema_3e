package net.sf.anathema.hero.charms.model.special.mechanics;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearningModel;
import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.health.model.HealthModelFetcher;
import net.sf.anathema.hero.health.model.IHealthLevelProvider;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;

public class AddsHealthLevelsByTraitMechanic implements IAddsHealthLevelsByTraitMechanic {

  private final TraitType traitType;
  private final Map<Integer, HealthLevelType[]> healthLevels;
  private final CharmName charmId;

  public AddsHealthLevelsByTraitMechanic(CharmName charmId, TraitType traitType, Map<Integer, HealthLevelType[]> healthLevels) {
    this.traitType = traitType;
    this.healthLevels = healthLevels;
    this.charmId = charmId;
  }
  
  @Override
	public void initialize(Hero hero) {
		HealthModelFetcher.fetch(hero).addHealthLevelProvider(new AddsHealthLevelsByTraitHealthProvider(hero));
	}

  @Override
  public TraitType getRelevantTrait() {
    return traitType;
  }

  @Override
  public Map<Integer, HealthLevelType[]> getHealthLevels() {
    return new LinkedHashMap<>(healthLevels);
  }

	private class AddsHealthLevelsByTraitHealthProvider implements IHealthLevelProvider
	{
		private final Hero hero;
		
		public AddsHealthLevelsByTraitHealthProvider(Hero hero) {
			this.hero = hero;
		}

		@Override
		public int getHealthLevelTypeCount(HealthLevelType type) {
			CharmsModel charms = CharmsModelFetcher.fetch(hero);
			TraitModel traits = TraitModelFetcher.fetch(hero);
			Charm baseCharm = charms.getCharmById(charmId);
			CharmSpecialLearningModel learning = charms.getCharmSpecialLearningModel(baseCharm);
			int picks = learning != null ? learning.getCurrentLearnCount() : (charms.isLearned(baseCharm) ? 1 : 0);
			int traitLevel = traits.getTrait(traitType).getCurrentValue();
			
			int total = 0;
			for (HealthLevelType entry : healthLevels.get(traitLevel)) {
				if (entry == type) {
					total += picks;
				}
			}
			return total;
		}
	}

	
}