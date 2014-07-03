package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.parser.template.CharmListTemplate;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.hero.template.ConfigurableTemplateLoader;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.lib.exception.AnathemaException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@ExtensibleDataSetCompiler
public class CharmCacheCompiler implements IExtensibleDataSetCompiler {

  private static final String Charm_File_Recognition_Pattern = "(.+?)\\.charms";
  private final List<ResourceFile> resourceFiles = new ArrayList<>();
  private final TemplateLoader<CharmListTemplate> charmsLoader = new ConfigurableTemplateLoader<>(CharmListTemplate.class);

  @SuppressWarnings("UnusedParameters")
  public CharmCacheCompiler(ObjectFactory objectFactory, IExtensibleDataSetProvider provider) {
    // nothing to do
  }

    @Override
  public String getName() {
    return "Charms";
  }

  @Override
  public String getRecognitionPattern() {
    return Charm_File_Recognition_Pattern;
  }

  @Override
  public void registerFile(ResourceFile resource) throws Exception {
    resourceFiles.add(resource);
  }

  @Override
  public ExtensibleDataSet build() {
    CharmCacheBuilder factory = new CharmCacheBuilder();
    resourceFiles.stream().forEach(resourceFile -> {
      CharmListTemplate template = loadTemplate(resourceFile);
      factory.addTemplate(template);
    });
    return factory.createCache();
    //return null;
  }

  private CharmListTemplate loadTemplate(ResourceFile resource) {
    InputStream inputStream = null;
    try {
      inputStream = resource.getURL().openStream();
      return charmsLoader.load(inputStream);
    } catch (IOException e) {
      throw new AnathemaException(e);
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
  }
}
