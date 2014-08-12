package net.sf.anathema.hero.abilities.template;

import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbilitiesTemplate extends GroupedTraitsTemplate {

  public List<CasteTraitTemplate> casteAbilities = new ArrayList<>();
  public Map<String, Integer> picks = new HashMap<>();
}
