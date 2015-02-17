package net.sf.anathema;

import com.google.inject.Guice;
import com.google.inject.Injector;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;
import net.sf.anathema.characterengine.integration.EngineModule;

public class IntegrationModule implements InjectorSource {

  @Override
  public Injector getInjector() {
    return Guice.createInjector(CucumberModules.SCENARIO, new EngineModule());
  }
}
