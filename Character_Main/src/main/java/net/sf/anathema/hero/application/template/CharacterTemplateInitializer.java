package net.sf.anathema.hero.application.template;

import net.sf.anathema.hero.application.CharacterTemplateResources;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.HeroSplatImpl;
import net.sf.anathema.hero.individual.template.HeroTemplate;
import net.sf.anathema.library.resources.ResourceFile;

public class CharacterTemplateInitializer {

  private final HeroEnvironment environment;
  private final GenericTemplateLoader<HeroTemplate> loader = new GenericTemplateLoader<>(HeroTemplate.class);

  public CharacterTemplateInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  public void addHeroSplats() {
    CharacterTemplateResources resourceFiles = environment.getDataSet(CharacterTemplateResources.class);
    for (ResourceFile templateResource : resourceFiles) {
      registerTemplateFromFile(templateResource);
    }
  }

  private void registerTemplateFromFile(ResourceFile templateResource) {
    HeroTemplate heroTemplate = loader.load(templateResource);
    HeroSplat splat = new HeroSplatImpl(heroTemplate, environment.getHeroTypes());
    environment.getTemplateRegistry().register(splat);
  }
}