package net.sf.anathema.hero.magic.parser.charms.special.repurchase;

import net.sf.anathema.charm.parser.template.special.Requirement;
import net.sf.anathema.charm.parser.template.special.SpecialCharmTemplate;
import net.sf.anathema.charm.parser.template.special.StaticRepurchase;
import net.sf.anathema.charm.parser.template.special.TierRepurchase;
import net.sf.anathema.charm.parser.template.special.TraitRepurchase;
import net.sf.anathema.hero.magic.parser.charms.special.SpecialCharmParser;
import net.sf.anathema.charm.parser.template.special.Repurchase;
import net.sf.anathema.charm.parser.template.special.Tier;
import net.sf.anathema.hero.traits.model.SystemConstants;
import net.sf.anathema.charm.parser.util.ElementUtilities;
import org.dom4j.Element;

@SuppressWarnings("UnusedDeclaration")
public class RepurchaseParser implements SpecialCharmParser {

  private static final String TAG_REPURCHASES = "repurchases";
  private static final String TAG_REPURCHASE = "repurchase";
  private static final String ATTRIB_ABSOLUTE_MAX = "absoluteMax";
  private static final String ATTRIB_LIMITING_TRAIT = "limitingTrait";
  private static final String ATTRIB_LIMIT = "limit";

  @Override
  public void parse(Element charmElement, SpecialCharmTemplate overallDto) {
    Element repurchasesElement = charmElement.element(TAG_REPURCHASES);
    overallDto.repurchase = createRepurchaseDto(repurchasesElement);
  }

  private Repurchase createRepurchaseDto(Element repurchasesElement) {
    Repurchase dto = new Repurchase();
    parseTraitRepurchase(dto, repurchasesElement);
    if (dto.traitRepurchase != null) {
      return dto;
    }
    parseStaticRepurchase(dto, repurchasesElement);
    if (dto.staticRepurchase != null) {
      return dto;
    }
    parseTierRepurchaseDto(dto, repurchasesElement);
    return dto;
  }

  private void parseTraitRepurchase(Repurchase dto, Element repurchasesElement) {
    String limitingTraitString = repurchasesElement.attributeValue(ATTRIB_LIMITING_TRAIT);
    if (limitingTraitString != null) {
      TraitRepurchase traitRepurchase = new TraitRepurchase();
      traitRepurchase.limitingTrait = limitingTraitString;
      traitRepurchase.modifier = parseModifier(repurchasesElement);
      traitRepurchase.absoluteMax = parseAbsoluteMaximum(repurchasesElement, traitRepurchase.modifier);
      dto.traitRepurchase = traitRepurchase;
    }
  }

  private void parseStaticRepurchase(Repurchase dto, Element repurchasesElement) {
    String limitString = repurchasesElement.attributeValue(ATTRIB_LIMIT);
    if (limitString != null) {
      StaticRepurchase staticRepurchase = new StaticRepurchase();
      staticRepurchase.limit = Integer.parseInt(limitString);
      dto.staticRepurchase = staticRepurchase;
    }
  }

  private void parseTierRepurchaseDto(Repurchase dto, Element repurchasesElement) {
    TierRepurchase repurchaseDto = new TierRepurchase();
    String trait = repurchasesElement.attributeValue(ATTRIB_TRAIT);
    for (Element repurchaseElement : ElementUtilities.elements(repurchasesElement, TAG_REPURCHASE)) {
      Tier tier = parseTierDto(trait, repurchaseElement);
      repurchaseDto.tiers.add(tier);
    }
    dto.tierRepurchase = repurchaseDto;
  }

  private Tier parseTierDto(String traitString, Element repurchase) {
    Tier tier = new Tier();
    int essence = ElementUtilities.getRequiredIntAttrib(repurchase, ATTRIB_ESSENCE);
    tier.requirements.add(createRequirementDto("Essence", essence));
    if (traitString != null) {
      int traitValue = ElementUtilities.getRequiredIntAttrib(repurchase, ATTRIB_TRAIT);
      tier.requirements.add(createRequirementDto(traitString, traitValue));
    }
    return tier;
  }

  private Requirement createRequirementDto(String traitType, int traitValue) {
    Requirement dto = new Requirement();
    dto.traitType = traitType;
    dto.traitValue = traitValue;
    return dto;
  }

  private int parseAbsoluteMaximum(Element repurchaseElement, int modifier) {
    int absoluteMax = SystemConstants.SYSTEM_ESSENCE_MAX + modifier;
    String maxString = repurchaseElement.attributeValue(ATTRIB_ABSOLUTE_MAX);
    try {
      absoluteMax = Integer.parseInt(maxString);
    } catch (Exception ignored) {
    }
    return absoluteMax;
  }

  private int parseModifier(Element repurchaseElement) {
    int modifier = 0;
    String modifierString = repurchaseElement.attributeValue(ATTRIB_MODIFIER);
    try {
      modifier = Integer.parseInt(modifierString);
    } catch (Exception ignored) {
    }
    return modifier;
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_REPURCHASES) != null;
  }
}