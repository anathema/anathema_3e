package net.sf.anathema.hero.template;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.framework.CharacterTemplateResources;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.resources.ResourceFile;

import java.io.IOException;
import java.io.InputStream;

public class CharacterTemplateInitializer {

  private final HeroEnvironment environment;
  private final GenericTemplateLoader<ParsedHeroTemplate> loader = new GenericTemplateLoader<>(ParsedHeroTemplate.class);

  public CharacterTemplateInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  public void addCharacterTemplates() {
    CharacterTemplateResources resourceFiles = environment.getDataSet(CharacterTemplateResources.class);
    for (ResourceFile templateResource : resourceFiles) {
      registerTemplateFromFile(templateResource);
    }
  }

  private void registerTemplateFromFile(ResourceFile templateResource) {
    try (InputStream stream = templateResource.getURL().openStream()) {
      ParsedHeroTemplate parsedHeroTemplate = loader.load(stream);
      HeroSplat template = new HeroSplatImpl(parsedHeroTemplate, environment.getCharacterTypes());
      environment.getTemplateRegistry().register(template);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }
}