package net.sf.anathema.hero.dummy;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.template.TemplateRegistry;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.io.DataFileProvider;
import net.sf.anathema.library.message.Messaging;
import net.sf.anathema.library.resources.Resources;
import org.mockito.Mockito;

public class DummyHeroEnvironment implements HeroEnvironment {

  public DummyHeroTypes characterTypes = new DummyHeroTypes();
  public DataFileProvider mockFileProvider = Mockito.mock(DataFileProvider.class);
  public ObjectFactory mockObjectFactory = Mockito.mock(ObjectFactory.class);

  @Override
  public HeroTypes getHeroTypes() {
    return characterTypes;
  }

  @Override
  public Messaging getMessaging() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getPreference(String key) {
    return null;
  }

  @Override
  public ObjectFactory getObjectFactory() {
    return mockObjectFactory;
  }

  @Override
  public Resources getResources() {
    throw new UnsupportedOperationException();
  }

  @Override
  public TemplateRegistry getTemplateRegistry() {
    throw new UnsupportedOperationException();
  }

  @Override
  public DataFileProvider getDataFileProvider() {
    return mockFileProvider;
  }

  @Override
  public <T extends ExtensibleDataSet> T getDataSet(Class<T> set) {
    throw new UnsupportedOperationException();
  }
}