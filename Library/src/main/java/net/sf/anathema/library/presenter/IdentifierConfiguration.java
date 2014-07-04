package net.sf.anathema.library.presenter;

import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.tooltip.ConfigurableTooltip;

public class IdentifierConfiguration extends AbstractUIConfiguration<Identifier> {
  private Resources resources;

  public IdentifierConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected void configureTooltipForExistingValue(Identifier value, ConfigurableTooltip configurableTooltip) {
    configurableTooltip.appendLine(getLabel(value));
  }

  @Override
  protected String labelForExistingValue(Identifier value) {
    return resources.getString(value.getId());
  }
}