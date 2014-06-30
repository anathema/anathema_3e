package net.sf.anathema.character.framework;

import net.sf.anathema.character.framework.xml.CharacterTemplateParser;
import net.sf.anathema.character.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class CharacterTemplateInitializer {

  private final HeroEnvironment environment;

  public CharacterTemplateInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  public void addCharacterTemplates() {
    CharacterTemplateParser parser = new CharacterTemplateParser(environment.getCharacterTypes());
    CharacterTemplateResources resourceFiles = environment.getDataSet(CharacterTemplateResources.class);
    for (ResourceFile templateResource : resourceFiles) {
      initCharacterTemplates(environment, templateResource, parser);
    }
  }

  private void initCharacterTemplates(HeroEnvironment environment, ResourceFile resource, CharacterTemplateParser parser) {
    try {
      GenericCharacterTemplate template = get(parser, resource);
      environment.getTemplateRegistry().register(template);
    } catch (PersistenceException e) {
      Logger.getLogger(CharacterTemplateInitializer.class).error(resource.getFileName(), e);
    }
  }

  public GenericCharacterTemplate get(CharacterTemplateParser parser, ResourceFile resource) throws PersistenceException {
    InputStream documentStream = null;
    String id = resource.getFileName();
    try {
      documentStream = resource.getURL().openStream();
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(documentStream, null);
      return parser.parseTemplate(document.getRootElement());
    } catch (Exception e) {
      throw new PersistenceException("Unable to find file " + id);
    } finally {
      IOUtils.closeQuietly(documentStream);
    }
  }
}