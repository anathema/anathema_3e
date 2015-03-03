package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.library.logging.Logger;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.tree.display.NodePresentationProperties;

import com.google.common.base.Preconditions;

import static java.text.MessageFormat.format;
import static net.sf.anathema.hero.charms.display.view.NodeIds.toCharmName;

public class DefaultNodePresentationProperties implements NodePresentationProperties {

  private static final Logger logger = Logger.getLogger(DefaultNodePresentationProperties.class);

  private final FunctionalNodeProperties properties;
  private final MagicDisplayLabeler charmLabeler;
  private final Resources resources;
  private final CharmMap map;

  public DefaultNodePresentationProperties(Resources resources, FunctionalNodeProperties properties, CharmMap map) {
    this.properties = properties;
    this.resources = resources;
    this.map = map;
    this.charmLabeler = new MagicDisplayLabeler(resources);
  }

  private boolean isTreeRoot(Charm charm) {
    return !charm.getPrerequisites().hasCharmPrerequisites();
  }

  @Override
  public String getNodeText(String nodeId) {
    if (properties.isRequirementNode(nodeId)) {
      return textForRequirementNode(nodeId);
    }
    Charm charm = findNonNullCharm(toCharmName(nodeId));
    String name = getNodeName(charm);
    if (isTreeRoot(charm)) {
      return name.toUpperCase();
    }
    return name;
  }

  private String textForRequirementNode(String nodeId) {
    String requirementWithCount = nodeId.replaceFirst(FunctionalNodeProperties.REQUIREMENT + ".", "");
    String[] strings = requirementWithCount.split("\\.");
    int requirementCount = Integer.parseInt(strings[strings.length - 1]);
    String essenceMinimum = "";
    // what is this case for?
    if (strings[1].startsWith("Essence")) {
    	int minimum = Integer.parseInt(strings[1].replaceFirst("Essence", ""));
    	essenceMinimum = resources.getString("Essence") + " " + minimum + "+ ";
    }
    String requirementNameKey = strings[0];
    String requirementName = resources.getString(FunctionalNodeProperties.REQUIREMENT + "." + requirementNameKey);
    String charmString = resources.getString(requirementCount == 1 ? "Charms.Charm.Single" : "Charms.Charm.Multiple");
    return resources.getString("Requirement.Message", requirementCount, essenceMinimum, requirementName, charmString);
  }

  private String getNodeName(Charm charm) {
    if (charmLabeler.supportsMagic(charm)) {
      return charmLabeler.getLabelForMagic(charm);
    }
    logger.warn(format("No resource key found for node {0}. It must be a requirement or a charm.", charm.getName().text));
    return resources.getString(charm.getName().text);
  }

  private Charm findNonNullCharm(CharmName charmId) {
    Charm charm = map.getCharmById(charmId);
    Preconditions.checkNotNull(charm, format("No Charm with id ''{0}'' found.", charmId));
    return charm;
  }
}