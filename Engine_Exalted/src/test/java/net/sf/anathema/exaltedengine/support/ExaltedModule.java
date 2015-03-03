package net.sf.anathema.exaltedengine.support;

import cucumber.runtime.java.guice.ScenarioScoped;

import net.sf.anathema.exaltedengine.ExaltedEngine;

import com.google.inject.AbstractModule;

@SuppressWarnings("UnusedDeclaration")
public class ExaltedModule extends AbstractModule {
  @Override
  protected void configure() {
      bind(ExaltedEngine.class).in(ScenarioScoped.class);
  }
}