package net.sf.anathema.hero.martial.display;

import net.sf.anathema.hero.martial.model.StyleName;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.resources.Resources;

public class MartialArtsStyleConfiguration extends AbstractUIConfiguration<StyleName> {

  private Resources resources;

  public MartialArtsStyleConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected String labelForExistingValue(StyleName value) {
    return resources.getString(value.name);
  }
}
