package net.sf.anathema.character.framework.xml.registry;

import net.sf.anathema.character.framework.ICharacterTemplateExtensionResourceCache;
import net.sf.anathema.character.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.framework.xml.experience.GenericExperiencePointCosts;

public class CharacterTemplateRegistryCollection implements ICharacterTemplateRegistryCollection {

  private final IXmlTemplateRegistry<GenericCharacterTemplate> characterTemplateRegistry;
  private final IXmlTemplateRegistry<GenericBonusPointCosts> bonusPointsRegistry;
  private final IXmlTemplateRegistry<GenericExperiencePointCosts> experienceTemplateRegistry;

  public CharacterTemplateRegistryCollection(ICharacterTemplateExtensionResourceCache cache) {
    characterTemplateRegistry = new XmlTemplateRegistry<>(cache);
    bonusPointsRegistry = new XmlTemplateRegistry<>(cache);
    experienceTemplateRegistry = new XmlTemplateRegistry<>(cache);
  }

  @Override
  public IXmlTemplateRegistry<GenericCharacterTemplate> getCharacterTemplateRegistry() {
    return characterTemplateRegistry;
  }

  @Override
  public IXmlTemplateRegistry<GenericBonusPointCosts> getBonusPointTemplateRegistry() {
    return bonusPointsRegistry;
  }

  @Override
  public IXmlTemplateRegistry<GenericExperiencePointCosts> getExperienceTemplateRegistry() {
    return experienceTemplateRegistry;
  }
}