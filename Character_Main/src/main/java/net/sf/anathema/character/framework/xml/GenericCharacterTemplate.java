package net.sf.anathema.character.framework.xml;

import net.sf.anathema.hero.template.ConfiguredModel;
import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.hero.template.TemplateType;

import java.util.ArrayList;
import java.util.List;

public class GenericCharacterTemplate implements HeroTemplate {

  private TemplateType templateType;
  private final List<ConfiguredModel> models = new ArrayList<>();

  @Override
  public TemplateType getTemplateType() {
    return templateType;
  }

  @Override
  public List<ConfiguredModel> getModels() {
    return new ArrayList<>(models);
  }

  public void setTemplateType(TemplateType templateType) {
    this.templateType = templateType;
  }

  public void addModel(String modelId, String templateId) {
    models.add(new ConfiguredModel(modelId, templateId));
  }
}
