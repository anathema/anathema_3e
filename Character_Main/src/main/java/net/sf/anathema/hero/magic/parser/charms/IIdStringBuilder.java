package net.sf.anathema.hero.magic.parser.charms;

import net.sf.anathema.hero.magic.charm.CharmException;
import org.dom4j.Element;

public interface IIdStringBuilder {

  String build(Element element) throws CharmException;
}