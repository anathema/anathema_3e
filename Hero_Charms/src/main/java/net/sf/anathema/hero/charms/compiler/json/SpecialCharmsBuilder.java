package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.template.special.SpecialCharmListTemplate;
import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;
import net.sf.anathema.hero.charms.compiler.special.AdditionalCharmFactory;
import net.sf.anathema.hero.charms.compiler.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.hero.charms.compiler.special.ReflectionSpecialMechanicsBuilder;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;
import net.sf.anathema.hero.individual.persistence.values.ReflectionValueBuilder;
import net.sf.anathema.library.initialization.ObjectFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.List;

public class SpecialCharmsBuilder {

  private ReflectionSpecialCharmBuilder learningBuilder;
  private ReflectionSpecialMechanicsBuilder mechanicsBuilder;
  private ReflectionValueBuilder valueBuilder;
  private List<CharmSpecialLearning> specialLearningCharms = new ArrayList<>();
  private Multimap<String, CharmSpecialMechanic> specialMechanics = ArrayListMultimap.create();

  public SpecialCharmsBuilder(ObjectFactory objectFactory) {
    this.learningBuilder = new ReflectionSpecialCharmBuilder(objectFactory);
    this.mechanicsBuilder = new ReflectionSpecialMechanicsBuilder(objectFactory);
    this.valueBuilder = new ReflectionValueBuilder(objectFactory);
  }

  public void addTemplate(SpecialCharmListTemplate listTemplate, AdditionalCharmFactory factory) {
    listTemplate.charms.forEach((name, template) -> {
    	template.charmId = name;
    	CharmSpecialLearning special = learningBuilder.readCharmLearning(template, factory,
    			id -> handleMechanics(template, id));
    	if (special != null) {
    		specialLearningCharms.add(special);
    	}
    	
    	handleMechanics(template, name);
    });
  }
  
  private void handleMechanics(SpecialCharmTemplate template, String id) {
  	List<CharmSpecialMechanic> mechanics = mechanicsBuilder.readCharmMechanics(template, id, valueBuilder);
  	if (!mechanics.isEmpty()) {
  		specialMechanics.putAll(id, mechanics);
  	}
  }

  public void addToCache(CharmCacheImpl cache) {
  	specialLearningCharms.stream().forEach(cache::addSpecialLearning);
  	specialMechanics.keySet().stream().forEach(charmName ->
  	cache.addSpecialMechanics(charmName, specialMechanics.get(charmName)));
  }
}
