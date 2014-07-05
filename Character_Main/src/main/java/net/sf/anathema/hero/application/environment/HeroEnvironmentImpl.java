package net.sf.anathema.hero.application.environment;

import net.sf.anathema.hero.application.InjectingObjectFactory2;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetProvider;
import net.sf.anathema.hero.environment.template.TemplateRegistry;
import net.sf.anathema.hero.environment.template.TemplateRegistryImpl;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.io.DataFileProvider;
import net.sf.anathema.library.message.Messaging;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

public class HeroEnvironmentImpl implements HeroEnvironment {

  private final TemplateRegistry templateRegistry = new TemplateRegistryImpl();
  private final ExtensibleDataSetProvider dataSetProvider;
  private final HeroTypes heroTypes;
  private ApplicationModel model;
  private Environment environment;

  public HeroEnvironmentImpl(ApplicationModel model, Environment environment, ExtensibleDataSetProvider dataSetProvider) {
    this.model = model;
    this.environment = environment;
    this.dataSetProvider = dataSetProvider;
    this.heroTypes = dataSetProvider.getDataSet(HeroTypes.class);
  }

  @Override
  public Resources getResources() {
    return environment;
  }

  @Override
  public TemplateRegistry getTemplateRegistry() {
    return templateRegistry;
  }

  @Override
  public ObjectFactory getObjectFactory() {
    return new InjectingObjectFactory2(environment.getObjectFactory(), model, environment);
  }

  @Override
  public HeroTypes getHeroTypes() {
    return heroTypes;
  }

  @Override
  public Messaging getMessaging() {
    return model.getMessaging();
  }

  @Override
  public String getPreference(String key) {
    return environment.getPreference(key);
  }

  @Override
  public DataFileProvider getDataFileProvider() {
    return model.getRepository();
  }

  @Override
  public <T extends ExtensibleDataSet> T getDataSet(Class<T> set) {
    return dataSetProvider.getDataSet(set);
  }
}
