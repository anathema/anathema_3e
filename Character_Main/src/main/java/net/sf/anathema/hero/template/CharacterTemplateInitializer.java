package net.sf.anathema.hero.template;

import net.sf.anathema.character.framework.CharacterTemplateResources;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.framework.HeroEnvironment;

public class CharacterTemplateInitializer {

  private final HeroEnvironment environment;

  public CharacterTemplateInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  public void addCharacterTemplates() {
    HeroTemplateGson gson = new HeroTemplateGson();
    CharacterTemplateResources resourceFiles = environment.getDataSet(CharacterTemplateResources.class);
    for (ResourceFile templateResource : resourceFiles) {
      ParsedHeroTemplate parsedHeroTemplate = gson.fromJson(templateResource.getURL());
      HeroTemplate template = new HeroTemplateImpl(parsedHeroTemplate, environment.getCharacterTypes());
      environment.getTemplateRegistry().register(template);
    }
  }
}