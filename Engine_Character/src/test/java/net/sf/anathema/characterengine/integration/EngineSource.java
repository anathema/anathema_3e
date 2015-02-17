package net.sf.anathema.characterengine.integration;

import com.google.inject.Guice;
import com.google.inject.Injector;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

public class EngineSource implements InjectorSource {
  @Override
  public Injector getInjector() {
    return Guice.createInjector(CucumberModules.SCENARIO, new EngineModule());
  }
}
