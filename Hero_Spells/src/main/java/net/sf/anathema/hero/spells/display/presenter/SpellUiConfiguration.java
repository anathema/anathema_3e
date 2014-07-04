package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.library.resources.Resources;

public class SpellUiConfiguration extends AbstractUIConfiguration<Spell> {

  private final Resources resources;
  private final SpellTooltipBuilder tooltipBuilder;

  public SpellUiConfiguration(Resources resources, SpellTooltipBuilder tooltipBuilder) {
    this.resources = resources;
    this.tooltipBuilder = tooltipBuilder;
  }

  @Override
  protected String labelForExistingValue(Spell value) {
    return resources.getString(value.getName().text);
  }

  @Override
  protected void configureTooltipForExistingValue(Spell value, ConfigurableTooltip configurableTooltip) {
    tooltipBuilder.createTooltip(value, configurableTooltip);
  }
}