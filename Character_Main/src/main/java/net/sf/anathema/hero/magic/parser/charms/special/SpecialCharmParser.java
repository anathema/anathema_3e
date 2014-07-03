package net.sf.anathema.hero.magic.parser.charms.special;

import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import org.dom4j.Element;

public interface SpecialCharmParser {
  String ATTRIB_NAME = "name";
  String ATTRIB_MODIFIER = "modifier";
  String ATTRIB_TRAIT = "trait";
  String ATTRIB_ESSENCE = "essence";

  void parse(Element charmElement, SpecialCharmTemplate overallDto);

  boolean supports(Element charmElement);
}
