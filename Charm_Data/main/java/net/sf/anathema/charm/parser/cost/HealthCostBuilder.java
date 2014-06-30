package net.sf.anathema.charm.parser.cost;

import net.sf.anathema.charm.data.cost.HealthCost;
import net.sf.anathema.charm.data.cost.HealthCostImpl;
import net.sf.anathema.charm.data.cost.HealthCostType;
import net.sf.anathema.charm.parser.ICharmXMLConstants;
import net.sf.anathema.charm.parser.util.ElementUtilities;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class HealthCostBuilder {

  public HealthCost buildCost(Element element) throws PersistenceException {
    if (element == null) {
      return HealthCostImpl.NULL_HEALTH_COST;
    }
    int cost = ElementUtilities.getRequiredIntAttrib(element, ICharmXMLConstants.ATTRIB_COST);
    String text = element.attributeValue(ICharmXMLConstants.ATTRIB_TEXT);
    boolean permanent = ElementUtilities.getBooleanAttribute(element, ICharmXMLConstants.ATTRIB_PERMANENT, false);
    String typeString = element.attributeValue(ICharmXMLConstants.ATTRIB_TYPE);
    HealthCostType type = typeString == null ? HealthCostType.Lethal : HealthCostType.valueOf(typeString);
    return new HealthCostImpl(cost, text, permanent, type);
  }
}