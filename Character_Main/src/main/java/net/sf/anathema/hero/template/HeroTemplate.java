package net.sf.anathema.hero.template;

import net.sf.anathema.hero.template.experience.IExperiencePointCosts;

import java.util.List;

public interface HeroTemplate {

  TemplateType getTemplateType();

  List<ConfiguredModel> getModels();

  IExperiencePointCosts getExperienceCost();
}