package net.sf.anathema.hero.flaws.compiler.json;

import net.sf.anathema.hero.application.environment.Inject;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.flaws.compiler.template.FlawListTemplate;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.platform.dependencies.InterfaceFinder;

import java.util.ArrayList;
import java.util.List;

@net.sf.anathema.platform.initialization.ExtensibleDataSetCompiler
public class FlawCacheCompiler implements ExtensibleDataSetCompiler {

  private static final String Flaw_File_Recognition_Pattern = ".+?\\.flaws";
  private final List<ResourceFile> resourceFiles = new ArrayList<>();
  @Inject
  public InterfaceFinder finder;

  @Override
  public String getName() {
    return "Flaws";
  }

  @Override
  public String getRecognitionPattern() {
    return Flaw_File_Recognition_Pattern;
  }

  @Override
  public void registerFile(ResourceFile resource) {
    resourceFiles.add(resource);
  }

  @Override
  public ExtensibleDataSet build() {
    TemplateLoader<FlawListTemplate> flawsLoader = new GenericTemplateLoader<>(FlawListTemplate.class);
    FlawCacheBuilder flawsBuilder = new FlawCacheBuilder();
    resourceFiles.forEach(resourceFile -> {
      FlawListTemplate flawsTemplate = flawsLoader.load(resourceFile);
      flawsBuilder.addTemplate(flawsTemplate);
    });
    return flawsBuilder.createCache();
  }
}