package net.sf.anathema.hero.charms.display.view;

import java.util.List;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.AnyOneTraitCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.AttributeKnownCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.EvocationTierPrerequisite;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.prerequisite.TraitGroupCharmPrerequisite;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.display.prerequisites.NonCharmPrerequisiteId;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

public class NodeIds {

  public static String getNodeId(AttributeKnownCharmPrerequisite prerequisite) {
    NonCharmPrerequisiteId id = new NonCharmPrerequisiteId();
    prerequisite.process(id);
    return id.id;
  }
  
  public static String getNodeId(EvocationTierPrerequisite prerequisite) {
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
  
  public static String getNodeId(TraitGroupCharmPrerequisite prerequisite) {
  	return getNodeId(prerequisite.getTraits(), prerequisite.getThreshold(), prerequisite.getEssenceMinimum());
  }
  
  public static String getNodeId(List<RequiredTraitType> traits, int threshold, int minimumEssence) {
  	String label = "";
  	for (RequiredTraitType trait : traits) {
  		label += trait.type;
  	}
  	
  	if (minimumEssence > 1) {
  		return "Requirement." + label + ".Essence" + minimumEssence + "." + threshold;
  	}
  	return "Requirement." + label + "." + threshold;
  }
  
  public static String getNodeId(AnyOneTraitCharmPrerequisite prerequisite) {
  	return "Requirement.Group." + prerequisite.getThreshold(); 
  }
  
  public static String getNodeIdForAnyOneTrait(int threshold) {
  	return "Requirement.AnyOneAbility." + threshold; 
	}
  
}
