package net.sf.anathema.hero.merits.compiler.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.TypeAdapterFactory;

import net.sf.anathema.hero.application.environment.Inject;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.health.template.HealthLevelReader;
import net.sf.anathema.hero.individual.persistence.GenericAdapter;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.platform.persistence.PolymorphicTypeAdapterFactoryFactory;
import net.sf.anathema.platform.persistence.RuntimeTypeAdapterFactory;
import net.sf.anathema.hero.merits.compiler.json.template.MeritListTemplate;
import net.sf.anathema.hero.merits.compiler.json.template.requirements.MeritRequirementsTemplate;
import net.sf.anathema.hero.merits.compiler.template.mechanics.MeritMechanicalDetailTemplate;
import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.platform.dependencies.InterfaceFinder;

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
        GenericAdapter<HealthLevelType> adapter = new GenericAdapter<>(HealthLevelType.class, new HealthLevelReader());
        TemplateLoader<MeritListTemplate> meritsLoader = new GenericTemplateLoader<>(MeritListTemplate.class, 
        		new GenericAdapter[] { adapter }, factories);
		MeritCacheBuilder meritsBuilder = new MeritCacheBuilder();
	    resourceFiles.forEach(resourceFile -> meritsBuilder.addTemplate(loadTemplate(resourceFile, meritsLoader)));
        return meritsBuilder.createCache();
	}
	
	private <T> T loadTemplate(ResourceFile resource, TemplateLoader<T> loader) {
	    try (InputStream inputStream = resource.getURL().openStream()) {
	      return loader.load(inputStream);
	    } catch (IOException e) {
	      throw new PersistenceException(e);
	    }
	  }

}
