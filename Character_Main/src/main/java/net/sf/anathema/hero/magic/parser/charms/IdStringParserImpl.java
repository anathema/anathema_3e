package net.sf.anathema.hero.magic.parser.charms;

import net.sf.anathema.hero.magic.charm.CharmException;
import net.sf.anathema.charm.parser.ICharmXMLConstants;
import net.sf.anathema.lib.lang.StringUtilities;
import org.dom4j.Element;

public class IdStringParserImpl implements IdStringParser {

  @Override
  public String build(Element element) throws CharmException {
    String value = element.attributeValue(ICharmXMLConstants.ATTRIB_ID);
    if (StringUtilities.isNullOrTrimmedEmpty(value)) {
      throw new CharmException("Id must not be empty.");
    }
    return value;
  }
}