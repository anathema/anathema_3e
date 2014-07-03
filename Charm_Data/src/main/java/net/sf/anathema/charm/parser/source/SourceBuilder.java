package net.sf.anathema.charm.parser.source;

import net.sf.anathema.magic.source.SourceBook;
import net.sf.anathema.magic.source.SourceBookImpl;
import net.sf.anathema.charm.parser.util.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.charm.parser.ICharmXMLConstants.ATTRIB_SOURCE;
import static net.sf.anathema.charm.parser.ICharmXMLConstants.TAG_SOURCE;

public class SourceBuilder {

  public SourceBook[] buildSourceList(Element magicElement) {
    List<SourceBook> sources = new ArrayList<>();
    List<Element> sourceElements = ElementUtilities.elements(magicElement, TAG_SOURCE);
    for (Element sourceElement : sourceElements) {
      String source = sourceElement.attributeValue(ATTRIB_SOURCE);
      sources.add(new SourceBookImpl(source));
    }
    return sources.toArray(new SourceBook[sources.size()]);
  }
}