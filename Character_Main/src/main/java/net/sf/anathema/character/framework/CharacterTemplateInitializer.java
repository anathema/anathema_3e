package net.sf.anathema.character.framework;

import net.sf.anathema.character.framework.xml.CharacterTemplateParser;
import net.sf.anathema.character.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;

import java.io.InputStream;

public class CharacterTemplateInitializer {

  private final HeroEnvironment environment;

  public CharacterTemplateInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  public void addCharacterTemplates() {
    CharacterTemplateResources templateResources = environment.getDataSet(CharacterTemplateResources.class);
    for (ResourceFile templateResource : templateResources) {
      registerParsedTemplate(environment, templateResource);
    }
  }

  private void registerParsedTemplate(HeroEnvironment environment, ResourceFile resource) {
    CharacterTemplateParser parser = new CharacterTemplateParser(environment.getCharacterTypes());
    initCharacterTemplates(environment, resource, parser);
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
      Document document = DocumentUtilities.read(documentStream);
      return parser.parseTemplate(document.getRootElement());
    } catch (Exception e) {
      throw new PersistenceException("Unable to find file " + id);
    } finally {
      IOUtils.closeQuietly(documentStream);
    }
  }
}