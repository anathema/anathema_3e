package net.sf.anathema.characterengine.integration;

import cucumber.runtime.java.guice.ScenarioScoped;

import net.sf.anathema.characterengine.engine.DefaultEngine;
import net.sf.anathema.characterengine.engine.Engine;

import com.google.inject.AbstractModule;

public class EngineModule extends AbstractModule {
  @Override
  protected void configure() {
      bind(Engine.class).to(DefaultEngine.class).in(ScenarioScoped.class);
      bind(EngineCharacter.class).in(ScenarioScoped.class);
  }
}