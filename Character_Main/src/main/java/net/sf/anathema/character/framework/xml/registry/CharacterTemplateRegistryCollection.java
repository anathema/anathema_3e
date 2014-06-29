package net.sf.anathema.character.framework.xml.registry;

import net.sf.anathema.character.framework.ICharacterTemplateExtensionResourceCache;
import net.sf.anathema.character.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.framework.xml.GenericCharacterTemplate;

public class CharacterTemplateRegistryCollection implements ICharacterTemplateRegistryCollection {

  private final IXmlTemplateRegistry<GenericCharacterTemplate> characterTemplateRegistry;

  public CharacterTemplateRegistryCollection(ICharacterTemplateExtensionResourceCache cache) {
    characterTemplateRegistry = new XmlTemplateRegistry<>(cache);
  }

  @Override
  public IXmlTemplateRegistry<GenericCharacterTemplate> getCharacterTemplateRegistry() {
    return characterTemplateRegistry;
  }
}