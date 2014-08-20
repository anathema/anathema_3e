package net.sf.anathema.hero.spells.template;

import net.sf.anathema.hero.spells.data.CircleType;

import java.util.Map;
import java.util.TreeMap;

public class SpellsTemplate {

	public Map<CircleType, CharmLearnedInitiationTemplate> charmInitiations = new TreeMap<>();
  public Map<CircleType, MeritLearnedInitiationTemplate> meritInitiations = new TreeMap<>();
  public String favoringTrait;
}
