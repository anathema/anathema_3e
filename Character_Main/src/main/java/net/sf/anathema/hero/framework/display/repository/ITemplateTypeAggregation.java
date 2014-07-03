package net.sf.anathema.hero.framework.display.repository;

import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.hero.template.PresentationProperties;
import net.sf.anathema.hero.template.TemplateType;

public interface ITemplateTypeAggregation {

  TemplateType getTemplateType();

  PresentationProperties getPresentationProperties();

  boolean contains(HeroTemplate template);
}