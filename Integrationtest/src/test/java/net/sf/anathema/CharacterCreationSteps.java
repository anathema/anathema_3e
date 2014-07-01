package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.hero.framework.Character;
import net.sf.anathema.integration.CharacterFactory;
import net.sf.anathema.integration.concept.ConceptModelUtilities;

public class CharacterCreationSteps {
  private final CharacterHolder holder;
  private CharacterFactory characterFactory;

  @Inject
  public CharacterCreationSteps(CharacterHolder holder) {
    this.holder = holder;
  }

  @Before
  public void startAnathema() {
    this.characterFactory = new CharacterFactory();
    this.characterFactory.startAnathema();
  }

  @Given("Anathema is running")
  public void Anathema_is_running() {
    //for readable tests only
  }

  @Given("^a new God-Blooded of any kind$")
  public void I_create_a_new_god_blooded() throws Throwable {
    I_create_a_new_character_with_subtype("Lunar", "HalfCasteLunar");
  }

  @Given("^a new (.*) using rules for (.*)$")
  public void I_create_a_new_character_with_subtype(String type, String subtype) throws Throwable {
    Character character = characterFactory.createCharacter(type, subtype);
    holder.setCharacter(character);
  }

  @Given("^any Solar with Caste (.*)$")
  public void I_create_any_Solar_with_Caste(String caste) throws Throwable {
    Character character = characterFactory.createCharacter("Solar", "RookieLawgiver");
    ConceptModelUtilities.setCaste(character, caste);
    holder.setCharacter(character);
  }

  @Then("^I can create a new (.*) using rules for (.*)$")
  public void I_can_create_a_new_character(String type, String subtype) throws Throwable {
    characterFactory.createCharacter(type, subtype);
  }

  @When("^I save and reload the character$")
  public void I_save_and_reload_the_character() throws Throwable {
    Character reloadedCharacter = characterFactory.saveAndReload(holder.getHero());
    holder.setCharacter(reloadedCharacter);
  }

  @Given("^a new Character of any kind$")
  public void a_new_Character_of_any_kind() throws Throwable {
    I_create_a_new_character_with_subtype("Solar", "RookieLawgiver");
  }

  @After
  public void deleteRepository() throws Throwable {
    this.characterFactory.tearDownRepository();
  }
}