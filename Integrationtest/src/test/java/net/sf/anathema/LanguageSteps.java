package net.sf.anathema;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import net.sf.anathema.charm.data.reference.SpellName;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import com.google.inject.Inject;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LanguageSteps {

  private final CharacterHolder character;

  @Inject
  public LanguageSteps(CharacterHolder character) {
    this.character = character;
  }

  @When("^she learns the Language of (.*)$")
  public void she_learns_the_Language_of(String id) throws Throwable {
    character.getLanguages().selectLanguage(new SimpleIdentifier(id));
    character.getLanguages().commitSelection();
  }

  @When("^she forgets the Language of (.*)$")
  public void she_forgets_the_Language_of(String id) throws Throwable {
  	character.getLanguages().removeEntry(new SimpleIdentifier(id));
  }

  @Then("^she can learn the Language of (.*)$")
  public void she_can_learn_the_Language_of(String id) throws Throwable {
    character.getLanguages().selectLanguage(new SimpleIdentifier(id));
    boolean learnable = character.getLanguages().isEntryAllowed();
    assertThat(learnable, is(true));
  }

  @Then("^she can not learn the Language of (.*)$")
  public void she_can_not_learn_the_Language_of(String id) throws Throwable {
  	character.getLanguages().selectLanguage(new SimpleIdentifier(id));
    boolean learnable = character.getLanguages().isEntryAllowed();
    assertThat(learnable, is(false));
  }

  @Then("^she knows the Language of (.*)$")
  public void she_knows_the_Language_of(String id) throws Throwable {
    boolean learned = character.getLanguages().getEntries().contains(new SimpleIdentifier(id));
    assertThat(learned, is(true));
  }

  @Then("^she does not know the Language of (.*)$")
  public void she_does_not_know_the_Language_of(String id) throws Throwable {
  	boolean learned = character.getLanguages().getEntries().contains(new SimpleIdentifier(id));
    assertThat(learned, is(false));
  }
  
  @Then("^she has (\\d+) Language picks$")
  public void she_has_Language_picks(int targetAmount) throws Throwable {
  	int avaliableAmount = character.getLanguages().getLanguagePointsAllowed();
    assertThat(avaliableAmount == targetAmount, is(true));
  }
}