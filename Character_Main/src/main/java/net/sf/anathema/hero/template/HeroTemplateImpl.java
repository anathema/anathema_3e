package net.sf.anathema.hero.template;

import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeroTemplateImpl implements HeroTemplate {
  private final TemplateTypeImpl templateType;
  private final List<ConfiguredModel> models = new ArrayList<>();

  public HeroTemplateImpl(ParsedHeroTemplate parsedHeroTemplate, CharacterTypes characterTypes) {
    CharacterType type = characterTypes.findById(parsedHeroTemplate.characterType);
    this.templateType = new TemplateTypeImpl(type, new SimpleIdentifier(parsedHeroTemplate.template));
    for (Map.Entry<String, String> entry : parsedHeroTemplate.models.entrySet()) {
      models.add(new ConfiguredModel(entry.getKey(), entry.getValue()));
    }
  }

  @Override
  public TemplateType getTemplateType() {
    return templateType;
  }

  @Override
  public List<ConfiguredModel> getModels() {
    return models;
  }
}
