package net.sf.anathema.hero.magic.parser.charms.special.transcendence;

import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.charm.template.special.Transcendence;
import net.sf.anathema.hero.magic.parser.charms.special.SpecialCharmParser;
import org.dom4j.Element;

@SuppressWarnings("UnusedDeclaration")
public class TranscendenceParser implements SpecialCharmParser {

  private static final String TAG_TRANSCENDENCE = "transcendence";

  @Override
  public void parse(Element charmElement, SpecialCharmTemplate overallDto) {
    Element transcendenceElement = charmElement.element(TAG_TRANSCENDENCE);
    overallDto.transcendence = createTranscendenceDto(transcendenceElement, overallDto);
  }

  private Transcendence createTranscendenceDto(Element transcendenceElement, SpecialCharmTemplate overallDto) {
    Transcendence dto = new Transcendence();
    dto.trait = getGenericTraitType(overallDto.charmId);
    dto.modifier = Integer.parseInt(transcendenceElement.attributeValue(ATTRIB_MODIFIER));
    return dto;
  }

  private String getGenericTraitType(String value) {
    String[] split = value.split("\\.");
    return split[split.length - 1];
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_TRANSCENDENCE) != null;
  }
}