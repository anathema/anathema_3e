package net.sf.anathema.hero.magic.parser.charms.prerequisite;

import net.sf.anathema.hero.magic.charm.CharmException;
import org.dom4j.Element;

public interface ICharmPrerequisiteBuilder {

  String[] buildCharmPrerequisites(Element parent) throws CharmException;
}
