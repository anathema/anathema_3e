package net.sf.anathema.hero.template;

import net.sf.anathema.hero.environment.CharacterTypes;
import net.sf.anathema.hero.environment.template.SplatTypeImpl;
import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.hero.individual.splat.ConfiguredModel;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeroSplatImpl implements HeroSplat {
  private final SplatTypeImpl templateType;
  private final List<ConfiguredModel> models = new ArrayList<>();

  public HeroSplatImpl(ParsedHeroTemplate parsedHeroTemplate, CharacterTypes characterTypes) {
    CharacterType type = characterTypes.findById(parsedHeroTemplate.characterType);
    this.templateType = new SplatTypeImpl(type, new SimpleIdentifier(parsedHeroTemplate.template));
    for (Map.Entry<String, String> entry : parsedHeroTemplate.models.entrySet()) {
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
