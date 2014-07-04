package net.sf.anathema.hero.application.creation.models;

import net.sf.anathema.hero.application.creation.DefaultHero;
import net.sf.anathema.hero.application.template.DefaultTemplateFactory;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.splat.ConfiguredModel;
import net.sf.anathema.hero.individual.splat.HeroSplat;

import java.util.ArrayList;
import java.util.List;

public class HeroModelInitializer {

  private HeroEnvironment environment;
  private HeroSplat template;

  public HeroModelInitializer(HeroEnvironment environment, HeroSplat template) {
    this.environment = environment;
    this.template = template;
  }

  public void addModels(DefaultHero hero) {
    ModelFactoryAutoCollector collector = new ModelFactoryAutoCollector(environment.getObjectFactory());
    ModelFactoryMap factoryMap = new ModelFactoryMap(collector);
    Iterable<ConfiguredModel> sortedRelevantModelIds = getSortedModelIdsForHero(factoryMap);
    Iterable<HeroModel> sortedModels = createSortedModels(environment, factoryMap, sortedRelevantModelIds);
    initializeModelsInOrder(hero, sortedModels);
  }

  private Iterable<ConfiguredModel> getSortedModelIdsForHero(ModelFactoryMap factoryMap) {
    return new ModelInitializationList<>(template.getModels(), factoryMap);
  }

  private Iterable<HeroModel> createSortedModels(HeroEnvironment generics, ModelFactoryMap factoryMap, Iterable<ConfiguredModel> sortedRelevantModelIds) {
    TemplateFactory templateFactory = new DefaultTemplateFactory(generics);
    List<HeroModel> modelList = new ArrayList<>();
    for (ConfiguredModel configuredModel : sortedRelevantModelIds) {
      factoryMap.assertContainsRequiredModel(configuredModel.modelId.getId());
      HeroModelFactory factory = factoryMap.get(configuredModel.modelId.getId());
      modelList.add(factory.create(templateFactory, configuredModel.modelTemplateId));
    }
    return modelList;
  }

  private void initializeModelsInOrder(DefaultHero hero, Iterable<HeroModel> modelList) {
    for (HeroModel model : modelList) {
      model.initialize(environment, hero);
      model.initializeListening(hero.getChangeAnnouncer());
      hero.addModel(model);
    }
  }
}