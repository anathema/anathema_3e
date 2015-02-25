package net.sf.anathema.hero.thaumaturgy.compiler.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.application.environment.Inject;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.hero.thaumaturgy.compiler.json.template.RitualListTemplate;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.platform.dependencies.InterfaceFinder;

@net.sf.anathema.platform.initialization.ExtensibleDataSetCompiler
public class ThaumaturgyCacheCompiler implements ExtensibleDataSetCompiler {

  private static final String Ritual_File_Recognition_Pattern = ".+?\\.rituals";
  private final List<ResourceFile> resourceFiles = new ArrayList<>();
  @Inject
  public InterfaceFinder finder;

  @Override
  public String getName() {
    return "Rituals";
  }

  @Override
  public String getRecognitionPattern() {
    return Ritual_File_Recognition_Pattern;
  }

  @Override
  public void registerFile(ResourceFile resource) {
    resourceFiles.add(resource);
  }

  @Override
  public ExtensibleDataSet build() {
    TemplateLoader<RitualListTemplate> ritualsLoader = new GenericTemplateLoader<>(RitualListTemplate.class);
    ThaumaturgyCacheBuilder ritualsBuilder = new ThaumaturgyCacheBuilder();
    resourceFiles.forEach(resourceFile -> {
      RitualListTemplate ritualsTemplate = ritualsLoader.load(resourceFile);
      ritualsBuilder.addTemplate(ritualsTemplate);
    });
    return ritualsBuilder.createCache();
  }
}