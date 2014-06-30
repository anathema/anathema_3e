package net.sf.anathema.hero.magic.parser.charms.prerequisite;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.charm.parser.util.ElementUtilities;
import org.dom4j.Element;

import static net.sf.anathema.charm.parser.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.charm.parser.ICharmXMLConstants.ATTRIB_VALUE;

public class TraitPrerequisiteBuilder implements ITraitPrerequisiteBuilder {
  private final TraitTypeUtils traitUtils = new TraitTypeUtils();

  @Override
  public ValuedTraitType build(Element element) throws PersistenceException {
    TraitType propertyType = traitUtils.getTraitTypeById(element.attributeValue(ATTRIB_ID));
    int minValue = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_VALUE);
    return new net.sf.anathema.hero.traits.model.types.ValuedTraitType(propertyType, minValue);
  }
}