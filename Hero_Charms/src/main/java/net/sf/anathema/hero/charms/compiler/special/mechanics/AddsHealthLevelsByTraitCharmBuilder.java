package net.sf.anathema.hero.charms.compiler.special.mechanics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.charm.template.special.mechanics.AddsHealthLevelsByTraitTemplate;
import net.sf.anathema.hero.charms.compiler.special.CharmSpecialMechanicsBuilder;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;
import net.sf.anathema.hero.charms.model.special.mechanics.AddsHealthLevelsByTraitMechanic;
import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.individual.persistence.values.ValueFactory;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.model.TraitType;

@SuppressWarnings("UnusedDeclaration")
public class AddsHealthLevelsByTraitCharmBuilder implements CharmSpecialMechanicsBuilder {
  private static final String TAG_ZERO_HEALTH = "zeroHealthLevel";
  private static final String TAG_ONE_HEALTH = "oneHealthLevel";
  private static final String TAG_TWO_HEALTH = "twoHealthLevel";
  private static final String TAG_FOUR_HEALTH = "fourHealthLevel";
  private static final String TAG_INCAP_HEALTH = "incapHealthLevel";
  private static final String TAG_DYING_HEALTH = "dyingHealthLevel";

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public CharmSpecialMechanic readCharm(SpecialCharmTemplate overallDto,
  		String id,
  		ValueFactory valueFactory) {
    return createSpecialCharm(new CharmName(id), overallDto.addsHealthLevelsByTrait);
  }

  private CharmSpecialMechanic createSpecialCharm(CharmName id, AddsHealthLevelsByTraitTemplate dto) {
    TraitType trait = traitTypeFinder.getTrait(dto.trait);

    Map<String, HealthLevelType> healthTypeByString = getHealthTypeMap();
    LinkedHashMap<Integer, HealthLevelType[]> healthLevelsByTrait = new LinkedHashMap<>();
    for (Integer traitLevel : dto.grantedLevels.keySet()) {
      List<HealthLevelType> healthLevels = new ArrayList<>();
      for (String healthLevel : dto.grantedLevels.get(traitLevel)) {
        healthLevels.add(healthTypeByString.get(healthLevel));
       }
       healthLevelsByTrait.put(traitLevel, healthLevels.toArray(new HealthLevelType[healthLevels.size()]));
    }
    return new AddsHealthLevelsByTraitMechanic(id, trait, healthLevelsByTrait);
  }

  private Map<String, HealthLevelType> getHealthTypeMap() {
    Map<String, HealthLevelType> healthTypeByString = new HashMap<>();
    healthTypeByString.put(TAG_ZERO_HEALTH, HealthLevelType.ZERO);
    healthTypeByString.put(TAG_ONE_HEALTH, HealthLevelType.ONE);
    healthTypeByString.put(TAG_TWO_HEALTH, HealthLevelType.TWO);
    healthTypeByString.put(TAG_FOUR_HEALTH, HealthLevelType.FOUR);
    healthTypeByString.put(TAG_INCAP_HEALTH, HealthLevelType.INCAPACITATED);
    healthTypeByString.put(TAG_DYING_HEALTH, HealthLevelType.DYING);
    return healthTypeByString;
  }

  @Override
  public boolean supports(SpecialCharmTemplate overallDto) {
    return overallDto.addsHealthLevelsByTrait != null;
  }
}