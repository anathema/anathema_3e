package net.sf.anathema.hero.magic.parser.charms;

import net.sf.anathema.hero.magic.charm.CharmException;
import org.dom4j.Element;

public interface IdStringParser {

  String build(Element element) throws CharmException;
}