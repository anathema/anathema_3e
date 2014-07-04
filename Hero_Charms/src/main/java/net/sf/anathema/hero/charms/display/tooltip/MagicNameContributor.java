package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.tooltip.ConfigurableTooltip;
import net.sf.anathema.magic.data.Magic;

public class MagicNameContributor implements MagicTooltipContributor {
  private final MagicDisplayLabeler labeler;

  public MagicNameContributor(Resources resources) {
    this.labeler = new MagicDisplayLabeler(resources);
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object details) {
    tooltip.appendTitleLine(labeler.getLabelForMagic(magic));
  }
}