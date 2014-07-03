package net.sf.anathema.hero.magic.parser.charms;

import net.sf.anathema.charm.parser.ICharmXMLConstants;
import net.sf.anathema.hero.magic.charm.CharmException;
import net.sf.anathema.hero.magic.charm.duration.Duration;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import static net.sf.anathema.charm.parser.util.ElementUtilities.getRequiredAttrib;

public class DurationParser {

  public Duration buildDuration(Element durationElement) throws PersistenceException {
    if (durationElement == null) {
      throw new CharmException("Duration not specified for Charm");
    }
    String durationString = durationElement.attributeValue(ICharmXMLConstants.ATTRIB_DURATION);
    if (durationString != null) {
      return new Duration(durationString);
    }
    String amount = durationElement.attributeValue(ICharmXMLConstants.ATTRIB_AMOUNT);
    if (amount != null) {
      String unit = getRequiredAttrib(durationElement, ICharmXMLConstants.ATTRIB_UNIT);
      return new Duration(amount + " " + unit);
    }
    String event = durationElement.attributeValue(ICharmXMLConstants.ATTRIB_EVENT);
    if (event != null) {
      return new Duration("Until " + event);
    }
    throw new PersistenceException("No legal duration definition found");
  }
}