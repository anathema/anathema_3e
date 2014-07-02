package net.sf.anathema.hero.magic.parser.charms.prerequisite;

import net.sf.anathema.hero.magic.charm.CharmException;
import net.sf.anathema.hero.magic.charm.prerequisite.AttributeKnownCharmPrerequisite;

import org.dom4j.Element;

public interface IAttributePrerequisiteBuilder {

  AttributeKnownCharmPrerequisite[] getCharmAttributePrerequisites(Element prerequisitesElement) throws CharmException;

}