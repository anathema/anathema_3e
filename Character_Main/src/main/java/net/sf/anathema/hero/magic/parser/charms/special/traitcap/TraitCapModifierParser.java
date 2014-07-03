package net.sf.anathema.hero.magic.parser.charms.special.traitcap;

import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.charm.template.special.TraitCapModifier;
import net.sf.anathema.hero.magic.parser.charms.special.SpecialCharmParser;
import org.dom4j.Element;

@SuppressWarnings("UnusedDeclaration")
public class TraitCapModifierParser implements SpecialCharmParser {

  private static final String TAG_TRAIT_CAP_MODIFIER = "traitCapModifier";
  private static final String ATTRIB_MODIFIER = "modifier";
  private static final String ATTRIB_TRAIT = "trait";

  @Override
  public void parse(Element charmElement, SpecialCharmTemplate overallDto) {
    Element traitCapElement = charmElement.element(TAG_TRAIT_CAP_MODIFIER);
    overallDto.traitCapModifier = createCapModifierDto(traitCapElement, overallDto);
  }

  private TraitCapModifier createCapModifierDto(Element element, SpecialCharmTemplate overallDto) {
    TraitCapModifier dto = new TraitCapModifier();
    dto.trait = readTraitString(overallDto.charmId, element);
    dto.modifier = Integer.parseInt(element.attributeValue(ATTRIB_MODIFIER));
    return dto;
  }

  private String readTraitString(String id, Element traitCapModifierElement) {
    String traitString = traitCapModifierElement.attributeValue(ATTRIB_TRAIT);
    if (traitString == null) {
      traitString = id.split("\\.")[2];
    }
    return traitString;
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_TRAIT_CAP_MODIFIER) != null;
  }
}