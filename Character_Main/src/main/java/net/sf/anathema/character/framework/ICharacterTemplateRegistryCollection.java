package net.sf.anathema.character.framework;

import net.sf.anathema.character.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.framework.xml.registry.IXmlTemplateRegistry;

public interface ICharacterTemplateRegistryCollection {

  IXmlTemplateRegistry<GenericCharacterTemplate> getCharacterTemplateRegistry();
}