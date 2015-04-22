package net.sf.anathema.hero.martial.display;

import net.sf.anathema.hero.martial.model.StyleName;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.resources.Resources;

public class MartialArtsStyleConfiguration extends AbstractUIConfiguration<Trait> {

  private Resources resources;

  public MartialArtsStyleConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected String labelForExistingValue(Trait value) {
    return resources.getString(value.getType().getId());
  }
}
