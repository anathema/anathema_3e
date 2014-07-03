package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.template.special.SpecialCharmListTemplate;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;
import net.sf.anathema.hero.charms.compiler.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

import java.util.ArrayList;
import java.util.List;

public class SpecialCharmsBuilder {

  private ReflectionSpecialCharmBuilder builder;
  private List<ISpecialCharm> specialCharms = new ArrayList<>();

  public SpecialCharmsBuilder(ObjectFactory objectFactory) {
    this.builder = new ReflectionSpecialCharmBuilder(objectFactory);
  }

  public void addTemplate(SpecialCharmListTemplate listTemplate) {
    listTemplate.specialCharms.forEach(template -> specialCharms.add(builder.readCharm(template)));
  }

  public void addToCache(CharmCacheImpl cache) {
    specialCharms.stream().forEach(cache::addSpecial);
  }
}
