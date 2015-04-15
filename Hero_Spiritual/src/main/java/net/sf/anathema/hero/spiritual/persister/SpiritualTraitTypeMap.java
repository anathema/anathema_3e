package net.sf.anathema.hero.spiritual.persister;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.persistence.TraitTypeMap;

import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Essence;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Willpower;

public class SpiritualTraitTypeMap implements TraitTypeMap {

  private Map<String, TraitType> traitTypeMap = new HashMap<>();

  {
    addTraitType(Essence);
    addTraitType(Willpower);
  }

  private void addTraitType(TraitType type) {
    traitTypeMap.put(type.getId(), type);
  }

  @Override
  public TraitType get(String id) {
    return traitTypeMap.get(id);
  }
}
