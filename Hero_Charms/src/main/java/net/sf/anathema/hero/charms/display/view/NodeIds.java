package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.magic.data.attribute.MagicAttribute;
import net.sf.anathema.hero.charms.display.prerequisites.NonCharmPrerequisiteId;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.AttributeKnownCharmPrerequisite;

public class NodeIds {

  public static String getNodeId(AttributeKnownCharmPrerequisite prerequisite) {
    NonCharmPrerequisiteId id = new NonCharmPrerequisiteId();
    prerequisite.process(id);
    return id.id;
  }

  public static CharmName toCharmName(String nodeId) {
    return new CharmName(nodeId);
  }

  public static String getNodeId(MagicAttribute attribute, int count) {
    return "Requirement." + attribute.getId() + "." + count;
  }

  public static String getNodeId(Charm prerequisite) {
    return prerequisite.getName().text;
  }
}
