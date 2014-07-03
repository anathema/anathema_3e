package net.sf.anathema.hero.magic.parser.charms.special.multi;

import net.sf.anathema.charm.template.special.MultiEffect;
import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.magic.parser.charms.special.SpecialCharmParser;
import org.dom4j.Element;

@SuppressWarnings("UnusedDeclaration")
public class MultiEffectParser implements SpecialCharmParser {

  private static final String TAG_MULTI_EFFECT = "multiEffects";
  private static final String TAG_EFFECT = "effect";
  private static final String ATTRIB_PREREQ_EFFECT = "prereqEffect";
  private static final String ATTRIB_NAME = "name";

  @Override
  public void parse(Element charmElement, SpecialCharmTemplate overallDto) {
    Element multiEffectElement = charmElement.element(TAG_MULTI_EFFECT);
    overallDto.multiEffect = createMultiEffectDto(multiEffectElement);
  }

  private MultiEffect createMultiEffectDto(Element multiEffectElement) {
    MultiEffect dto = new MultiEffect();
    for (Object effectObj : multiEffectElement.elements(TAG_EFFECT)) {
      Element effect = (Element) effectObj;
      dto.effects.add(effect.attributeValue(ATTRIB_NAME));
      dto.prerequisiteEffectMap.put(effect.attributeValue(ATTRIB_NAME), effect.attributeValue(ATTRIB_PREREQ_EFFECT));
    }
    return dto;
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_MULTI_EFFECT) != null;
  }
}