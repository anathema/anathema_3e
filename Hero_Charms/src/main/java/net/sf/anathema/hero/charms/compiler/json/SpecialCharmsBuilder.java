package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.template.special.SpecialCharmListTemplate;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;
import net.sf.anathema.hero.charms.compiler.special.AdditionalCharmFactory;
import net.sf.anathema.hero.charms.compiler.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.library.initialization.ObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class SpecialCharmsBuilder {

  private ReflectionSpecialCharmBuilder builder;
  private List<ISpecialCharm> specialCharms = new ArrayList<>();

  public SpecialCharmsBuilder(ObjectFactory objectFactory) {
    this.builder = new ReflectionSpecialCharmBuilder(objectFactory);
  }

  public void addTemplate(SpecialCharmListTemplate listTemplate, AdditionalCharmFactory factory) {
    listTemplate.charms.forEach((name, template) -> {
    	template.charmId = name;
    	ISpecialCharm special = builder.readCharm(template, factory);
    	if (special != null) {
    		specialCharms.add(special);
    	}
    });
  }

  public void addToCache(CharmCacheImpl cache) {
    specialCharms.stream().forEach(cache::addSpecial);
  }
}
