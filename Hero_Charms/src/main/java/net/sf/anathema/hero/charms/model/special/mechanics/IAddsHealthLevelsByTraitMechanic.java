package net.sf.anathema.hero.charms.model.special.mechanics;

import java.util.Map;

import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;
import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.traits.model.TraitType;

public interface IAddsHealthLevelsByTraitMechanic extends CharmSpecialMechanic {

  TraitType getRelevantTrait();

  Map<Integer, HealthLevelType[]> getHealthLevels();
}