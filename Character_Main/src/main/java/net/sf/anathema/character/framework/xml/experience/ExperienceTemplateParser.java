package net.sf.anathema.character.framework.xml.experience;

import net.sf.anathema.character.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.framework.xml.util.CostParser;
import net.sf.anathema.hero.template.experience.CurrentRatingCost;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class ExperienceTemplateParser extends AbstractXmlTemplateParser<GenericExperiencePointCosts> {

  private static final String TAG_GENERAL_COSTS = "generalCosts";
  private static final String TAG_FAVORED_COSTS = "favoredCosts";
  private static final String TAG_ABILITIES = "abilities";
  private static final String TAG_SPECIALTIES = "specialties";
  private final CostParser costParser = new CostParser();

  public ExperienceTemplateParser(IXmlTemplateRegistry<GenericExperiencePointCosts> templateRegistry) {
    super(templateRegistry);
  }

  @Override
  protected GenericExperiencePointCosts createNewBasicTemplate() {
    return new GenericExperiencePointCosts();
  }

  @Override
  public GenericExperiencePointCosts parseTemplate(Element element) throws PersistenceException {
    GenericExperiencePointCosts costs = getBasicTemplate(element);
    setAbilityCosts(element, costs);
    return costs;
  }

  private void setAbilityCosts(Element element, GenericExperiencePointCosts costs) throws PersistenceException {
    Element abilities = element.element(TAG_ABILITIES);
    if (abilities == null) {
      return;
    }
    setSpecialtyCosts(abilities, costs);
  }

  private void setSpecialtyCosts(Element abilities, GenericExperiencePointCosts costs) throws PersistenceException {
    int specialtyCost = costParser.getFixedCostFromRequiredElement(abilities, TAG_SPECIALTIES);
    costs.setSpecialtyCosts(specialtyCost);
  }

  protected final CurrentRatingCost getFavoredCost(Element attributes) throws PersistenceException {
    return new CostParser().getMultiplyRatingCostsFromRequiredElement(attributes, TAG_FAVORED_COSTS);
  }

  protected final CurrentRatingCost getGeneralCost(Element attributes) throws PersistenceException {
    return new CostParser().getMultiplyRatingCostsFromRequiredElement(attributes, TAG_GENERAL_COSTS);
  }
}