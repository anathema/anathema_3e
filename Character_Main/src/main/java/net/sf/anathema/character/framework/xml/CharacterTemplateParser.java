package net.sf.anathema.character.framework.xml;

import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.character.framework.type.CharacterTypes;
import net.sf.anathema.hero.template.TemplateType;
import net.sf.anathema.hero.template.TemplateTypeImpl;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class CharacterTemplateParser {

  private final CharacterTypes characterTypes;

  public CharacterTemplateParser(CharacterTypes characterTypes) {
    this.characterTypes = characterTypes;
  }

  public GenericCharacterTemplate parseTemplate(Element element) throws PersistenceException {
    GenericCharacterTemplate characterTemplate = new GenericCharacterTemplate();
    updateTemplateType(element, characterTemplate);
    parseModels(element, characterTemplate);
    return characterTemplate;
  }

  private void updateTemplateType(Element element, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    String characterTypeId = ElementUtilities.getRequiredAttrib(element, "characterType");
    CharacterType characterType = characterTypes.findById(characterTypeId);
    String subtemplateValue = element.attributeValue("subtemplate");
    Identifier subtemplate = new SimpleIdentifier(subtemplateValue);
    TemplateType templateType =new TemplateTypeImpl(characterType, subtemplate);
    characterTemplate.setTemplateType(templateType);
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