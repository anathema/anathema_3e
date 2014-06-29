package net.sf.anathema.character.framework.xml;

import net.sf.anathema.character.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.framework.type.CharacterTypes;
import net.sf.anathema.character.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.framework.xml.experience.ExperienceTemplateParser;
import net.sf.anathema.character.framework.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.hero.template.TemplateType;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class CharacterTemplateParser extends AbstractXmlTemplateParser<GenericCharacterTemplate> {

  private static final String TAG_EXPERIENCE = "experience";
  private static final String TAG_EXPERIENCE_POINT_COST = "experiencePointCost";

  private CharacterTypes characterTypes;
  private final ICharacterTemplateRegistryCollection registryCollection;

  public CharacterTemplateParser(CharacterTypes characterTypes, ICharacterTemplateRegistryCollection registryCollection) {
    super(registryCollection.getCharacterTemplateRegistry());
    this.characterTypes = characterTypes;
    this.registryCollection = registryCollection;
  }

  @Override
  protected GenericCharacterTemplate createNewBasicTemplate() {
    return new GenericCharacterTemplate();
  }

  private void parseExperience(Element element, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element creationElement = element.element(TAG_EXPERIENCE);
    if (creationElement == null) {
      return;
    }
    setExperiencePoints(characterTemplate, creationElement);
  }

  @Override
  public GenericCharacterTemplate parseTemplate(Element element) throws PersistenceException {
    GenericCharacterTemplate characterTemplate = new GenericCharacterTemplate();
    updateTemplateType(element, characterTemplate);
    parseModels(element, characterTemplate);
    parseExperience(element, characterTemplate);
    return characterTemplate;
  }

  private void updateTemplateType(Element element, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    TemplateType templateType = new TemplateTypeParser(characterTypes).parse(element);
    characterTemplate.setTemplateType(templateType);
  }

  private void setExperiencePoints(GenericCharacterTemplate characterTemplate, Element experienceElement) throws PersistenceException {
    Element experiencePointsElement = experienceElement.element(TAG_EXPERIENCE_POINT_COST);
    if (experiencePointsElement == null) {
      return;
    }
    ExperienceTemplateParser parser = new ExperienceTemplateParser(registryCollection.getExperienceTemplateRegistry());
    GenericExperiencePointCosts experienceTemplate = parser.parseTemplate(experiencePointsElement);
    characterTemplate.setExperiencePointCosts(experienceTemplate);
  }

  private void parseModels(Element element, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element modelsElement = element.element("models");
    if (modelsElement == null) {
      return;
    }
    for (Object modelElement : modelsElement.elements()) {
      String modelId = ((Element) modelElement).attributeValue("id");
      String modelTemplateId = ((Element) modelElement).attributeValue("template");
      characterTemplate.addModel(modelId, modelTemplateId);
    }
  }
}