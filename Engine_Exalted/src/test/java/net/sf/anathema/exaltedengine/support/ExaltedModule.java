package net.sf.anathema.exaltedengine.support;

import com.google.inject.AbstractModule;
import cucumber.runtime.java.guice.ScenarioScoped;
import net.sf.anathema.exaltedengine.ExaltedEngine;

@SuppressWarnings("UnusedDeclaration")
public class ExaltedModule extends AbstractModule {
  @Override
  protected void configure() {
      bind(ExaltedEngine.class).in(ScenarioScoped.class);
  }
}