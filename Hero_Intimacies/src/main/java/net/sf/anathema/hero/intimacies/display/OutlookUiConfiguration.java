package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.hero.intimacies.model.Outlook;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.resources.Resources;

public class OutlookUiConfiguration extends AbstractUIConfiguration<Outlook> {

  private Resources resources;

  public OutlookUiConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected String labelForExistingValue(Outlook value) {
    if (value == Outlook.Negative) {
      return resources.getString("Intimacies.Outlook.Negative");
    }
    return resources.getString("Intimacies.Outlook.Positive");
  }
}
