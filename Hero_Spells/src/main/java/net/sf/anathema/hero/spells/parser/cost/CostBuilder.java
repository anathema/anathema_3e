package net.sf.anathema.hero.spells.parser.cost;

import net.sf.anathema.charm.parser.ICharmXMLConstants;
import net.sf.anathema.charm.data.cost.Cost;
import net.sf.anathema.charm.data.cost.CostImpl;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.magic.spells.ElementUtilities;
import org.dom4j.Element;

public class CostBuilder {

  public Cost buildCost(Element element) throws PersistenceException {
    if (element == null) {
      return CostImpl.NULL_COST;
    }
    String costString = ElementUtilities.getRequiredAttrib(element, ICharmXMLConstants.ATTRIB_COST);
    String text = element.attributeValue(ICharmXMLConstants.ATTRIB_TEXT);
    boolean permanent = ElementUtilities.getBooleanAttribute(element, ICharmXMLConstants.ATTRIB_PERMANENT, false);
    return new CostImpl(costString, text, permanent);
  }
}