package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.library.tooltip.ConfigurableTooltip;

public interface CharmTooltipBuilder {
  void configureTooltipWithoutSpecials(Charm charm, ConfigurableTooltip tooltip);

  void configureTooltipWithSpecials(Charm charm, CharmSpecialLearning special, ConfigurableTooltip tooltip);
}