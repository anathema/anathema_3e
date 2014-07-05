package net.sf.anathema.hero.environment;

import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.template.TemplateRegistry;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.io.DataFileProvider;
import net.sf.anathema.library.message.Messaging;
import net.sf.anathema.library.resources.Resources;

public interface HeroEnvironment {

  Resources getResources();

  TemplateRegistry getTemplateRegistry();

  DataFileProvider getDataFileProvider();

  <T extends ExtensibleDataSet> T getDataSet(Class<T> set);

  ObjectFactory getObjectFactory();

  HeroTypes getHeroTypes();

  Messaging getMessaging();

  String getPreference(String key);
}