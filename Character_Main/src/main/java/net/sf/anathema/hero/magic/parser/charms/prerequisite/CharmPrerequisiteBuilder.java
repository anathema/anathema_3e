package net.sf.anathema.hero.magic.parser.charms.prerequisite;

import com.google.common.base.Strings;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.CharmException;
import net.sf.anathema.charm.parser.util.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static net.sf.anathema.charm.parser.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.charm.parser.ICharmXMLConstants.TAG_CHARM_REFERENCE;

public class CharmPrerequisiteBuilder implements ICharmPrerequisiteBuilder {

  @Override
  public final CharmName[] buildCharmPrerequisites(Element parent) throws CharmException {
    List<CharmName> prerequisiteCharmIds = new ArrayList<>();
    prerequisiteCharmIds.addAll(getCharmIds(parent));
    return prerequisiteCharmIds.toArray(new CharmName[prerequisiteCharmIds.size()]);
  }

  protected Collection<CharmName> getCharmIds(Element parent) throws CharmException {
    List<CharmName> prerequisiteCharmIds = new ArrayList<>();
    List<Element> prerequisiteCharmList = ElementUtilities.elements(parent, TAG_CHARM_REFERENCE);
    for (Element element : prerequisiteCharmList) {
      String id = element.attributeValue(ATTRIB_ID);
      if (Strings.isNullOrEmpty(id)) {
        throw new CharmException("Prerequisite charm id is null or empty.");
      }
      prerequisiteCharmIds.add(new CharmName(id));
    }
    return prerequisiteCharmIds;
  }
}