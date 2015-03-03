package net.sf.anathema.exaltedengine;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.exaltedengine.support.CharacterHolder;

import com.google.inject.Inject;

@ScenarioScoped
public class PersonaSteps {

  private final ExaltedEngine engine;
  private final CharacterHolder holder;

  @Inject
  public PersonaSteps(ExaltedEngine engine, CharacterHolder holder){
    this.engine = engine;
    this.holder = holder;
  }

  @Given("^an Exalted character$")
  public void an_Exalted_character() throws Throwable {
    I_create_an_Exalted_character();
  }

  @When("^I create an Exalted character$")
  public void I_create_an_Exalted_character() throws Throwable {
    Persona persona = engine.createCharacter();
    holder.hold(persona);
  }
}