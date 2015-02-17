package net.sf.anathema.characterengine.integration;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import cucumber.runtime.java.guice.ScenarioScope;
import cucumber.runtime.java.guice.ScenarioScoped;
import net.sf.anathema.characterengine.engine.DefaultEngine;
import net.sf.anathema.characterengine.engine.Engine;

public class EngineModule extends AbstractModule {
  @Override
  protected void configure() {
      bind(Engine.class).to(DefaultEngine.class).in(ScenarioScoped.class);
      bind(EngineCharacter.class).in(ScenarioScoped.class);
  }
}