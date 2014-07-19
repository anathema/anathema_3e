package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.hero.intimacies.model.Bond;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.Resources;

public class BondUiConfiguration extends AbstractUIConfiguration<Bond> {
  private Resources resources;

  public BondUiConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected String labelForExistingValue(Bond value) {
    if (value == Bond.Tie) {
      return resources.getString("Intimacies.Bond.Tie");
    }
    return resources.getString("Intimacies.Bond.Principle");
  }
}