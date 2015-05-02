package net.sf.anathema.hero.charms.model.special.mechanics;

import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.health.model.HealthModelFetcher;
import net.sf.anathema.hero.health.model.IHealthLevelProvider;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.magic.data.reference.CharmName;

import java.util.Map;

public class AddsHealthLevelsByTraitMechanic extends AbstractCharmMechanic {

  private final TraitType traitType;
  private final Map<Integer, HealthLevelType[]> healthLevels;

  public AddsHealthLevelsByTraitMechanic(CharmName charmId, TraitType traitType,
                                         Map<Integer, HealthLevelType[]> healthLevels) {
    super(charmId);
    this.traitType = traitType;
    this.healthLevels = healthLevels;
  }

  @Override
  public void initialize(Hero hero) {
    HealthModelFetcher.fetch(hero).addHealthLevelProvider(new AddsHealthLevelsByTraitHealthProvider(hero));
  }

  private class AddsHealthLevelsByTraitHealthProvider implements IHealthLevelProvider {
    private final Hero hero;

    public AddsHealthLevelsByTraitHealthProvider(Hero hero) {
      this.hero = hero;
    }

    @Override
    public int getHealthLevelTypeCount(HealthLevelType type) {
      TraitModel traits = TraitModelFetcher.fetch(hero);
      int picks = getLearnCount(hero);
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