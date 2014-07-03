package net.sf.anathema.hero.magic.parser.charms;

import net.sf.anathema.magic.attribute.MagicAttribute;
import net.sf.anathema.magic.attribute.MagicAttributeImpl;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.charm.parser.util.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.charm.parser.ICharmXMLConstants.ATTRIB_ATTRIBUTE;
import static net.sf.anathema.charm.parser.ICharmXMLConstants.ATTRIB_VALUE;
import static net.sf.anathema.charm.parser.ICharmXMLConstants.ATTRIB_VISUALIZE;
import static net.sf.anathema.charm.parser.ICharmXMLConstants.TAG_ATTRIBUTE;
import static net.sf.anathema.charm.parser.ICharmXMLConstants.TAG_GENERIC_ATTRIBUTE;

public class CharmAttributeParser {

  public MagicAttribute[] buildCharmAttributes(Element rulesElement, ValuedTraitType primaryPrerequisite) {
    List<MagicAttribute> attributes = new ArrayList<>();
    for (Element attributeElement : ElementUtilities.elements(rulesElement, TAG_ATTRIBUTE)) {
      String attributeId = attributeElement.attributeValue(ATTRIB_ATTRIBUTE);
      boolean visualizeAttribute = ElementUtilities.getBooleanAttribute(attributeElement, ATTRIB_VISUALIZE, false);
      String value = attributeElement.attributeValue(ATTRIB_VALUE);
      if (value == null || value.isEmpty()) {
        attributes.add(new MagicAttributeImpl(attributeId, visualizeAttribute));
      } else {
        attributes.add(new MagicAttributeImpl(attributeId, visualizeAttribute, value));
      }
    }
    if (primaryPrerequisite != null) {
      String id = primaryPrerequisite.getType().getId();
      attributes.add(new MagicAttributeImpl(id, false));
      for (Element genericAttributeElement : ElementUtilities.elements(rulesElement, TAG_GENERIC_ATTRIBUTE)) {
        String attributeId = genericAttributeElement.attributeValue(ATTRIB_ATTRIBUTE) + id;
        attributes.add(new MagicAttributeImpl(attributeId, false));
      }
    }
    return attributes.toArray(new MagicAttribute[attributes.size()]);
  }
}