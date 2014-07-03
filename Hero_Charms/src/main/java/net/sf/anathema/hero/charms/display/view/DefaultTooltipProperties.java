package net.sf.anathema.hero.charms.display.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.charms.display.tooltip.CharmTooltipBuilderImpl;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.NullSpecialCharm;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.platform.tree.display.ToolTipProperties;

import static net.sf.anathema.hero.charms.display.view.NodeIds.toCharmName;

public class DefaultTooltipProperties implements ToolTipProperties {
  private final FunctionalNodeProperties treeProperties;
  private final CharmMap map;
  private final CharmTooltipBuilderImpl tooltipTextProvider;
  private SpecialCharmSet specialCharmSet;

  public DefaultTooltipProperties(FunctionalNodeProperties treeProperties, CharmMap map, Resources resources,
                                  MagicDescriptionProvider magicDescriptionProvider, SpecialCharmSet specialCharmSet) {
    this.treeProperties = treeProperties;
    this.map = map;
    this.specialCharmSet = specialCharmSet;
    this.tooltipTextProvider = new CharmTooltipBuilderImpl(resources, magicDescriptionProvider);
  }

  @Override
  public void configureTooltip(String nodeId, ConfigurableTooltip tooltip) {
    if (treeProperties.isRequirementNode(nodeId)) {
      tooltip.showNoTooltip();
      return;
    }
    Charm charm = findNonNullCharm(toCharmName(nodeId));
    ISpecialCharm specialCharm = getSpecialCharm(toCharmName(nodeId));
    tooltipTextProvider.configureTooltipWithSpecials(charm, specialCharm, tooltip);
  }

  private Charm findNonNullCharm(CharmName charmId) {
    Charm charm = map.getCharmById(charmId);
    Preconditions.checkNotNull(charm, "Charm with id '" + charmId + "' not found.");
    return charm;
  }

  private ISpecialCharm getSpecialCharm(CharmName charmName) {
    for (ISpecialCharm special : specialCharmSet) {
      if (special.getCharmName().equals(charmName)) {
        return special;
      }
    }
    return new NullSpecialCharm();
  }
}