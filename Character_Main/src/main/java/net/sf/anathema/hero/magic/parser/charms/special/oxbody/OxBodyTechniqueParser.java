package net.sf.anathema.hero.magic.parser.charms.special.oxbody;

import net.sf.anathema.charm.template.special.OxBodyPick;
import net.sf.anathema.charm.template.special.OxBodyTechnique;
import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.magic.parser.charms.special.SpecialCharmParser;
import org.dom4j.Element;

import java.util.Arrays;

@SuppressWarnings("UnusedDeclaration")
public class OxBodyTechniqueParser implements SpecialCharmParser {

  private static final String TAG_OX_BODY_CHARM = "oxbody";
  private static final String TAG_OX_BODY_PICK = "pick";
  private static final String ATTRIB_TRAIT = "trait";
  private static final String ATTRIB_NAME = "name";

  @Override
  public void parse(Element charmElement, SpecialCharmTemplate overallDto) {
    Element oxBodyElement = charmElement.element(TAG_OX_BODY_CHARM);
    overallDto.oxBodyTechnique = createOxBodyTechniqueDto(oxBodyElement);
  }

  private OxBodyTechnique createOxBodyTechniqueDto(Element oxBodyElement) {
    OxBodyTechnique dto = new OxBodyTechnique();
    dto.traits.addAll(Arrays.asList(oxBodyElement.attributeValue(ATTRIB_TRAIT).split(",")));
    for (Object pickObj : oxBodyElement.elements(TAG_OX_BODY_PICK)) {
      Element pick = (Element) pickObj;
      createPickDto(dto, pick);
    }
    return dto;
  }

  private void createPickDto(OxBodyTechnique dto, Element pick) {
    OxBodyPick category = new OxBodyPick();
    category.id = pick.attributeValue(ATTRIB_NAME);
    for (Object levelObj : pick.elements()) {
      Element levelElement = (Element) levelObj;
      category.healthLevels.add(levelElement.getName());
    }
    dto.picks.add(category);
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_OX_BODY_CHARM) != null;
  }
}