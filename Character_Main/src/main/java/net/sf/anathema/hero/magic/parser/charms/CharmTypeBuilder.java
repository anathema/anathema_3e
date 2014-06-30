package net.sf.anathema.hero.magic.parser.charms;

import net.sf.anathema.hero.magic.charm.CharmException;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import org.dom4j.Element;

import static net.sf.anathema.charm.parser.ICharmXMLConstants.ATTRIB_TYPE;
import static net.sf.anathema.charm.parser.ICharmXMLConstants.TAG_CHARMTYPE;

public class CharmTypeBuilder {

  public CharmType build(Element rulesElement) throws CharmException {
    final Element typeElement = rulesElement.element(TAG_CHARMTYPE);
    try {
      return CharmType.valueOf(typeElement.attributeValue(ATTRIB_TYPE));
    } catch (IllegalArgumentException e) {
      throw new CharmException("Bad type in charm. (Type unreadable)");
    } catch (NullPointerException e) {
      throw new CharmException("Bad type in charm. (Element required)");
    }
  }
}