package net.sf.anathema.hero.magic.parser.charms.prerequisite;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.CharmException;
import org.dom4j.Element;

public interface ICharmPrerequisiteBuilder {

  CharmName[] buildCharmPrerequisites(Element parent) throws CharmException;
}
