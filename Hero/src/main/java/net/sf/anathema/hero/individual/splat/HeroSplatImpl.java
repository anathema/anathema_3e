package net.sf.anathema.hero.individual.splat;

import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.environment.template.SplatTypeImpl;
import net.sf.anathema.hero.individual.template.HeroTemplate;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeroSplatImpl implements HeroSplat {
  private final SplatTypeImpl templateType;
  private final List<ConfiguredModel> models = new ArrayList<>();

  public HeroSplatImpl(HeroTemplate heroTemplate, HeroTypes heroTypes) {
    HeroType type = heroTypes.findById(heroTemplate.characterType);
    this.templateType = new SplatTypeImpl(type, new SimpleIdentifier(heroTemplate.template));
    for (Map.Entry<String, String> entry : heroTemplate.models.entrySet()) {
      models.add(new ConfiguredModel(entry.getKey(), entry.getValue()));
    }
  }

  @Override
  public SplatType getTemplateType() {
    return templateType;
  }

  @Override
  public List<ConfiguredModel> getModels() {
    return models;
  }
}
