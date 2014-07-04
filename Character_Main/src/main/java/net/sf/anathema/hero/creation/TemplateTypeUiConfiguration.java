package net.sf.anathema.hero.creation;

import net.sf.anathema.hero.framework.presentation.GenericPresentationTemplate;
import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.resources.Resources;

public class TemplateTypeUiConfiguration extends AbstractUIConfiguration<HeroTemplate> {
  private Resources resources;

  public TemplateTypeUiConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected String labelForExistingValue(HeroTemplate heroTemplate) {
    GenericPresentationTemplate template = new GenericPresentationTemplate(heroTemplate);
    String newActionResource = template.getNewActionResource();
    return resources.getString(newActionResource);
  }
}