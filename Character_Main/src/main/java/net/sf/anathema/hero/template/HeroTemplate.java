package net.sf.anathema.hero.template;

import java.util.List;

public interface HeroTemplate {

  TemplateType getTemplateType();

  List<ConfiguredModel> getModels();
}