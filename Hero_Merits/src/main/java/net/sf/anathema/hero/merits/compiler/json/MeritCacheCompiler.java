package net.sf.anathema.hero.merits.compiler.json;

import net.sf.anathema.hero.application.environment.Inject;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.hero.merits.compiler.json.template.MeritListTemplate;
import net.sf.anathema.hero.merits.compiler.json.template.requirements.MeritRequirementsTemplate;
import net.sf.anathema.hero.merits.compiler.template.mechanics.MeritMechanicalDetailTemplate;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.platform.dependencies.InterfaceFinder;
import net.sf.anathema.platform.persistence.PolymorphicTypeAdapterFactoryFactory;
import net.sf.anathema.platform.persistence.RuntimeTypeAdapterFactory;

import java.util.ArrayList;
import java.util.List;

@net.sf.anathema.platform.initialization.ExtensibleDataSetCompiler
public class MeritCacheCompiler implements ExtensibleDataSetCompiler {

  private static final String Merit_File_Recognition_Pattern = ".+?\\.merits";
  private final List<ResourceFile> resourceFiles = new ArrayList<>();
  @Inject
  public InterfaceFinder finder;

  @Override
  public String getName() {
    return "Merits";
  }

  @Override
  public String getRecognitionPattern() {
    return Merit_File_Recognition_Pattern;
  }

  @Override
  public void registerFile(ResourceFile resource) {
    resourceFiles.add(resource);
  }

  @Override
  public ExtensibleDataSet build() {
    RuntimeTypeAdapterFactory[] factories =
            new PolymorphicTypeAdapterFactoryFactory(finder).generateFactories(MeritRequirementsTemplate.class, MeritMechanicalDetailTemplate.class);
    TemplateLoader<MeritListTemplate> meritsLoader = new GenericTemplateLoader<>(MeritListTemplate.class, factories);
    MeritCacheBuilder meritsBuilder = new MeritCacheBuilder();
    resourceFiles.forEach(resourceFile -> {
      MeritListTemplate meritsTemplate = meritsLoader.load(resourceFile);
      meritsBuilder.addTemplate(meritsTemplate);
    });
    return meritsBuilder.createCache();
  }
}