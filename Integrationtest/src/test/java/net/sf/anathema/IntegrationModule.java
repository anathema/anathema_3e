package net.sf.anathema;

import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

import net.sf.anathema.characterengine.integration.EngineModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class IntegrationModule implements InjectorSource {

  @Override
  public Injector getInjector() {
    return Guice.createInjector(CucumberModules.SCENARIO, new EngineModule());
  }
}
