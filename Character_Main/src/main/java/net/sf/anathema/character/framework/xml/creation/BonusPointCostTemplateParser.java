package net.sf.anathema.character.framework.xml.creation;

import net.sf.anathema.character.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.framework.xml.util.CostParser;
import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class BonusPointCostTemplateParser extends AbstractXmlTemplateParser<GenericBonusPointCosts> {

  private static final String TAG_ATTRIBUTES = "attributes";
  private static final String TAG_GENERAL_ATTRIBUTE = "generalAttribute";
  private static final String TAG_FAVORED_ATTRIBUTE = "favoredAttribute";
  private static final String TAG_ADVANTAGES = "advantages";
  private static final String TAG_WILLPOWER = "willpower";
  private static final String TAG_ESSENCE = "essence";

  private final CostParser costParser = new CostParser();

  public BonusPointCostTemplateParser(IXmlTemplateRegistry<GenericBonusPointCosts> registry) {
    super(registry);
  }

  @Override
  public GenericBonusPointCosts parseTemplate(Element element) throws PersistenceException {
    GenericBonusPointCosts costs = getBasicTemplate(element);
    setAttributeCost(element, costs);
    setAdvantageCosts(element, costs);
    return costs;

  }

  private void setAdvantageCosts(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element advantageElement = element.element(TAG_ADVANTAGES);
    if (advantageElement == null) {
      return;
    }
    setWillpowerCosts(advantageElement, costs);
    setEssenceCosts(advantageElement, costs);
  }

  private void setEssenceCosts(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element essenceElement = element.element(TAG_ESSENCE);
    if (essenceElement == null) {
      return;
    }
    CurrentRatingCosts essenceCost = costParser.getCosts(essenceElement);
    costs.setEssenceCosts(essenceCost);
  }

  private void setWillpowerCosts(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element willpowerElement = element.element(TAG_WILLPOWER);
    if (willpowerElement == null) {
      return;
    }
    int fixedCost = costParser.getFixedCostFromRequiredElement(element, TAG_WILLPOWER);
    costs.setWillpowerCosts(fixedCost);
  }

  private void setAttributeCost(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element attributeElement = element.element(TAG_ATTRIBUTES);
    if (attributeElement == null) {
      return;
    }
    int generalCost = costParser.getFixedCostFromRequiredElement(attributeElement, TAG_GENERAL_ATTRIBUTE);
    int favoredCost = costParser.getFixedCostFromOptionalElement(attributeElement, TAG_FAVORED_ATTRIBUTE, generalCost);
  }

  @Override
  protected GenericBonusPointCosts createNewBasicTemplate() {
    return new GenericBonusPointCosts();
  }
}