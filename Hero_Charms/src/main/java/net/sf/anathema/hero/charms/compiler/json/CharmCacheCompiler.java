package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.template.CharmListTemplate;
import net.sf.anathema.charm.template.special.SpecialCharmListTemplate;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.hero.template.GenericTemplateLoader;
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
  private final TemplateLoader<CharmListTemplate> charmsLoader = new GenericTemplateLoader<>(
    CharmListTemplate.class);
  private final TemplateLoader<SpecialCharmListTemplate> specialsLoader = new GenericTemplateLoader<>(
    SpecialCharmListTemplate.class);
  private final ObjectFactory objectFactory;

  @SuppressWarnings("UnusedParameters")
  public CharmCacheCompiler(ObjectFactory objectFactory, IExtensibleDataSetProvider provider) {
    this.objectFactory = objectFactory;
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
    CharmCacheBuilder charmsBuilder = new CharmCacheBuilder();
    SpecialCharmsBuilder specialBuilder = new SpecialCharmsBuilder(objectFactory);
    resourceFiles.stream().forEach(resourceFile -> {
      charmsBuilder.addTemplate(loadTemplate(resourceFile, charmsLoader));
      specialBuilder.addTemplate(loadTemplate(resourceFile, specialsLoader));
    });
    CharmCacheImpl charmCache = charmsBuilder.createCache();
    specialBuilder.addToCache(charmCache);
    return charmCache;
  }

  private <T> T loadTemplate(ResourceFile resource, TemplateLoader<T> loader) {
    InputStream inputStream = null;
    try {
      inputStream = resource.getURL().openStream();
      return loader.load(inputStream);
    } catch (IOException e) {
      throw new AnathemaException(e);
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
  }
}
