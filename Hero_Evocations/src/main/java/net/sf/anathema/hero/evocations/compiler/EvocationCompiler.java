package net.sf.anathema.hero.evocations.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charm.template.evocations.EvocationArtifactTemplate;
import net.sf.anathema.hero.application.environment.Inject;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;
import net.sf.anathema.hero.charms.evocations.json.EvocationsBuilder;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetProvider;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.platform.dependencies.InterfaceFinder;

@net.sf.anathema.platform.initialization.ExtensibleDataSetCompiler
@Weight(weight = 100)
public class EvocationCompiler implements ExtensibleDataSetCompiler {

  private static final String Charm_File_Recognition_Pattern = ".+?\\.charms";
  private final List<ResourceFile> resourceFiles = new ArrayList<>();
  
  @Inject
  public ObjectFactory objectFactory;
  @Inject
  public InterfaceFinder finder;
  @Inject
  public ExtensibleDataSetProvider cacheProvider;

  @Override
  public String getName() {
    return "Evocations";
  }

  @Override
  public String getRecognitionPattern() {
    return Charm_File_Recognition_Pattern;
  }

  @Override
  public void registerFile(ResourceFile resource) {
    resourceFiles.add(resource);
  }

  @Override
  public ExtensibleDataSet build() {
    TemplateLoader<EvocationArtifactTemplate> evocationLoader = new GenericTemplateLoader<>(EvocationArtifactTemplate.class);
    EvocationsBuilder evocationBuilder = new EvocationsBuilder();
    resourceFiles.forEach(resourceFile -> {
      evocationBuilder.addTemplate(loadTemplate(resourceFile, evocationLoader));
    });
    evocationBuilder.apply((CharmCacheImpl)cacheProvider.getDataSet(CharmCache.class));
    return new EvocationsCache();
  }

  private <T> T loadTemplate(ResourceFile resource, TemplateLoader<T> loader) {
    try (InputStream inputStream = resource.getURL().openStream()) {
      return loader.load(inputStream);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }
}