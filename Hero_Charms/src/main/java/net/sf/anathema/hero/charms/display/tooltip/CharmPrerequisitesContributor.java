package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.tooltip.ConfigurableTooltip;
import net.sf.anathema.magic.data.Magic;

public class CharmPrerequisitesContributor implements MagicTooltipContributor {
  private final Resources resources;

  public CharmPrerequisitesContributor(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object details) {
    if (!(magic instanceof Charm)) {
      return;
    }
    Charm charm = (Charm) magic;
    charm.getPrerequisites().forEachTraitPrerequisite(prerequisite -> {
      if (prerequisite.minimalValue == 0) {
        return;
      }
      String label = resources.getString("CharmTreeView.ToolTip.Minimum") + " " + resources.getString(prerequisite.type.type);
      String value = String.valueOf(prerequisite.minimalValue);
      tooltip.appendLine(label, value);
    });
  }
}