package net.sf.anathema.hero.spiritual.template;

import net.sf.anathema.hero.traits.template.TraitTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpiritualTraitsTemplate {

  public TraitTemplate willpower = new TraitTemplate();
  public TraitTemplate essence = new TraitTemplate();
  public Map<String, Integer> essenceValues =new LinkedHashMap<>();
}