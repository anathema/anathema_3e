package net.sf.anathema.hero.magic.parser.charms.special.paintolerance;

import net.sf.anathema.charm.parser.template.special.PainTolerance;
import net.sf.anathema.charm.parser.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.magic.parser.charms.special.SpecialCharmParser;
import org.dom4j.Element;

import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class PainToleranceParser implements SpecialCharmParser {

  private static final String ATTRIB_VALUE = "value";
  private static final String TAG_PAIN_TOLERANCE = "painTolerance";
  private static final String TAG_LEVEL = "level";

  @Override
  public void parse(Element charmElement, SpecialCharmTemplate overallDto) {
    Element painToleranceElement = charmElement.element(TAG_PAIN_TOLERANCE);
    overallDto.painTolerance = createPaintToleranceDto(painToleranceElement);
  }

  @SuppressWarnings("unchecked")
  private PainTolerance createPaintToleranceDto(Element painToleranceElement) {
    PainTolerance dto = new PainTolerance();
    List<Element> elements = painToleranceElement.elements(TAG_LEVEL);
    for (Element levelElement : elements) {
      dto.levels.add(parseValue(levelElement));
    }
    dto.learnCount = dto.levels.size();
    return dto;
  }

  private int parseValue(Element levelElement) {
    String attributeValue = levelElement.attributeValue(ATTRIB_VALUE);
    return Integer.parseInt(attributeValue);
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_PAIN_TOLERANCE) != null;
  }
}