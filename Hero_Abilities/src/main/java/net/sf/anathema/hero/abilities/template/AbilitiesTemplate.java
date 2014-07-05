package net.sf.anathema.hero.abilities.template;

import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;

import java.util.ArrayList;
import java.util.List;

public class AbilitiesTemplate extends GroupedTraitsTemplate {

  public int favoredCount = 0;
  public int casteCount = 0;
  public int supernalCount = 0;
  public List<CasteAbilitiesTemplate> casteAbilities = new ArrayList<>();
}
