package net.sf.anathema.hero.application;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetProvider;
import net.sf.anathema.hero.environment.template.TemplateRegistry;
import net.sf.anathema.hero.environment.template.TemplateRegistryImpl;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.io.DataFileProvider;

public class HeroEnvironmentImpl implements HeroEnvironment {

  private final TemplateRegistry templateRegistry = new TemplateRegistryImpl();
  private final DataFileProvider dataFileProvider;
  private final ExtensibleDataSetProvider dataSetProvider;
  private final ObjectFactory objectFactory;
  private final HeroTypes heroTypes;

  public HeroEnvironmentImpl(DataFileProvider dataFileProvider, ObjectFactory objectFactory,
                             ExtensibleDataSetProvider dataSetProvider) {
    this.objectFactory = objectFactory;
    this.dataFileProvider = dataFileProvider;
    this.dataSetProvider = dataSetProvider;
    this.heroTypes = dataSetProvider.getDataSet(HeroTypes.class);
  }

  @Override
  public TemplateRegistry getTemplateRegistry() {
    return templateRegistry;
  }

  @Override
  public ObjectFactory getObjectFactory() {
    return objectFactory;
  }

  @Override
  public HeroTypes getHeroTypes() {
    return heroTypes;
  }

  @Override
  public DataFileProvider getDataFileProvider() {
    return dataFileProvider;
  }

  @Override
  public <T extends ExtensibleDataSet> T getDataSet(Class<T> set) {
    return dataSetProvider.getDataSet(set);
  }
}
