package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.magic.data.Magic;

public interface MagicTooltipContributor {

  void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object specialDetails);
}
