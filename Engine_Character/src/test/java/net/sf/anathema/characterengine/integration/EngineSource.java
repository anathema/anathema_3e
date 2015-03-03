package net.sf.anathema.characterengine.integration;

import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class EngineSource implements InjectorSource {
  @Override
  public Injector getInjector() {
    return Guice.createInjector(CucumberModules.SCENARIO, new EngineModule());
  }
}
