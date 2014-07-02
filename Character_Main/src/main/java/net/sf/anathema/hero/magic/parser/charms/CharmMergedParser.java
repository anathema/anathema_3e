package net.sf.anathema.hero.magic.parser.charms;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.charm.parser.util.ElementUtilities;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.sf.anathema.charm.parser.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.charm.parser.ICharmXMLConstants.TAG_CHARM_REFERENCE;
import static net.sf.anathema.charm.parser.ICharmXMLConstants.TAG_MERGED;
import static net.sf.anathema.charm.parser.ICharmXMLConstants.TAG_MERGES;
import static net.sf.anathema.lib.lang.ArrayUtilities.getFirst;

public class CharmMergedParser {

  private final Logger logger = Logger.getLogger(CharmMergedParser.class);

  public void buildMerges(Document charmDoc, Charm[] charms) {
    Element charmListElement = charmDoc.getRootElement();
    readMerges(charmListElement, charms);
  }

  private void readMerges(Element charmListElement, Charm[] charms) {
    Element mergesElement = charmListElement.element(TAG_MERGES);
    if (mergesElement == null) {
      return;
    }
    for (Element mergedElement : ElementUtilities.elements(mergesElement, TAG_MERGED)) {
      readMerged(mergedElement, charms);
    }
  }

  private void readMerged(Element mergedElement, Charm[] existingCharms) {
    List<Element> charmReferences = ElementUtilities.elements(mergedElement, TAG_CHARM_REFERENCE);
    Set<Charm> charms = new HashSet<>(charmReferences.size());
    for (Element charmReference : charmReferences) {
      final String charmId = charmReference.attributeValue(ATTRIB_ID);
      Charm charm = getFirst(existingCharms, candidate -> candidate.getName().text.equals(charmId));
      if (charm == null) {
        logger.warn("Merge charm not found " + charmId);
        continue;
      }
      charms.add(charm);
    }
    for (Charm charm : charms) {
      ((CharmImpl) charm).addMerged(charms);
    }
  }
}