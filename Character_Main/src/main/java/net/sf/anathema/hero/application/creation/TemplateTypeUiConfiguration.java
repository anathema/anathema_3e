package net.sf.anathema.hero.application.creation;

import net.sf.anathema.hero.framework.presentation.GenericPresentationTemplate;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.resources.Resources;

public class TemplateTypeUiConfiguration extends AbstractUIConfiguration<HeroSplat> {
  private Resources resources;

  public TemplateTypeUiConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected String labelForExistingValue(HeroSplat heroSplat) {
    GenericPresentationTemplate template = new GenericPresentationTemplate(heroSplat);
    String newActionResource = template.getNewActionResource();
    return resources.getString(newActionResource);
  }
}