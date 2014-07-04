package net.sf.anathema.hero.environment;

import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.template.TemplateRegistry;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.io.DataFileProvider;

public interface HeroEnvironment {

  TemplateRegistry getTemplateRegistry();

  DataFileProvider getDataFileProvider();

  <T extends ExtensibleDataSet> T getDataSet(Class<T> set);

  ObjectFactory getObjectFactory();

  HeroTypes getHeroTypes();
}