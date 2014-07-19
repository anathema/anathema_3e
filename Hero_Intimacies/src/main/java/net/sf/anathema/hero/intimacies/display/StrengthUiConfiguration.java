package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.hero.intimacies.model.Strength;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.resources.Resources;

public class StrengthUiConfiguration extends AbstractUIConfiguration<Strength> {

  private Resources resources;

  public StrengthUiConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected String labelForExistingValue(Strength value) {
    switch (value){
      case Defining:
        return resources.getString("Intimacies.Strength.Defining");
      case Major:
        return resources.getString("Intimacies.Strength.Major");
      default:
        return resources.getString("Intimacies.Strength.Minor");
    }
  }
}
