package net.sf.anathema.hero.magic.parser.charms.prerequisite;

import net.sf.anathema.hero.magic.charm.CharmException;
import net.sf.anathema.hero.magic.charm.prerequisite.AttributeKnownCharmLearnPrerequisite;

import org.dom4j.Element;

public interface IAttributePrerequisiteBuilder {

  AttributeKnownCharmLearnPrerequisite[] getCharmAttributePrerequisites(Element prerequisitesElement) throws CharmException;

}