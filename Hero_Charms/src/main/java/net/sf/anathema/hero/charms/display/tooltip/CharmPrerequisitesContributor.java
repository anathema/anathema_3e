package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.TraitPrerequisite;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.data.Magic;

import java.util.List;

public class CharmPrerequisitesContributor implements MagicTooltipContributor {
  private final Resources resources;

  public CharmPrerequisitesContributor(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object details) {
    if (magic instanceof Charm) {
      Charm charm = (Charm) magic;
      createPrerequisiteLines(tooltip, charm.getPrerequisites().getTraitPrerequisites());
    }
  }

  private void createPrerequisiteLines(ConfigurableTooltip tooltip, List<TraitPrerequisite> prerequisites) {
    for (TraitPrerequisite prerequisite : prerequisites) {
      if (prerequisite.minimalValue == 0) {
        continue;
      }
      String label = resources.getString("CharmTreeView.ToolTip.Minimum")+" "+ resources.getString(prerequisite.type.type);
      String value = String.valueOf(prerequisite.minimalValue);
      tooltip.appendLine(label, value);
    }
  }
}