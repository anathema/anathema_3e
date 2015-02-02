package net.sf.anathema.hero.application.creation;

import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.hero.individual.view.HeroUI;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;

public class HeroCreationPageProperties {

  private final Resources resources;
  private final HeroUI iconProvider;

  public HeroCreationPageProperties(Resources resources) {
    this.resources = resources;
    this.iconProvider = new HeroUI();
  }

  public RelativePath getTypeIcon(HeroType type) {
    return iconProvider.getSmallTypeIconPath(type);
  }

  public AgnosticUIConfiguration<HeroSplat> getTemplateUI() {
    return new TemplateTypeUiConfiguration(resources);
  }
}