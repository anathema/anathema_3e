package net.sf.anathema.hero.charms.compiler.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charm.template.evocations.EvocationArtifactTemplate;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;

public class EvocationsBuilder {
  private List<EvocationArtifactTemplate> evocationCascades = new ArrayList<>();

  public void addTemplate(EvocationArtifactTemplate listTemplate) {
  	if (listTemplate.category.equals("Evocations")) {
  		evocationCascades.add(listTemplate);
  	}
  }

  public void addToCache(CharmCacheImpl cache) {
    evocationCascades.stream().forEach(cache::addEvocation);
  }
}
