package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.magic.basic.Magic;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.lib.gui.ConfigurableTooltip;

public class CharmTypeContributor implements ICharmTypeStringBuilder, MagicTooltipContributor {

  private final Resources resources;

  public CharmTypeContributor(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object details) {
    if (magic instanceof Charm) {
      String label = resources.getString("CharmTreeView.ToolTip.Type");
      String text = createTypeString(((Charm) magic).getCharmType());
      tooltip.appendLine(label, text);
    }
  }

  @Override
  public String createTypeString(CharmType charmType) {
    final StringBuilder builder = new StringBuilder();
    builder.append(getResources().getString(charmType.getId()));
    return builder.toString();
  }

  protected Resources getResources() {
    return resources;
  }
}