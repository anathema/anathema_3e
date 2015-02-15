package net.sf.anathema.exaltedengine.support;

import com.google.inject.Guice;
import com.google.inject.Injector;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

public class ExaltedSource implements InjectorSource {
  @Override
  public Injector getInjector() {
    return Guice.createInjector(CucumberModules.SCENARIO, new ExaltedModule());
  }
}
