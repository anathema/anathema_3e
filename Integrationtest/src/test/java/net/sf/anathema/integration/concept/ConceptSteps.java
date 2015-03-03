package net.sf.anathema.integration.concept;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

import net.sf.anathema.CharacterHolder;

import com.google.inject.Inject;

@ScenarioScoped
public class ConceptSteps {

  private final CharacterHolder character;

  @Inject
  public ConceptSteps(CharacterHolder character) {
    this.character = character;
  }

  @When("^she changes her caste to (.*)$")
  @Given("^I choose (.*) for her caste$")
  public void she_selects_her_caste(String casteId) {
    ConceptModelUtilities.setCaste(character.getHero(), casteId);
  }
}

