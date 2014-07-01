package net.sf.anathema.integration.concept;

import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.sf.anathema.CharacterHolder;

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

