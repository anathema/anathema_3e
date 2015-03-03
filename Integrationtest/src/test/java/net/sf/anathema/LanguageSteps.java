package net.sf.anathema;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.library.model.OptionalEntryReference;

import com.google.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ScenarioScoped
public class LanguageSteps {

  private final CharacterHolder character;

  @Inject
  public LanguageSteps(CharacterHolder character) {
    this.character = character;
  }

  @When("^she learns any language$")
  public void she_learns_any_language() throws Throwable {
    MeritsModel merits = character.getMerits();
    prepareForLanguage(merits, "Any");
    merits.commitSelection();
  }

  @Then("^she can learn the Language of (.*)$")
  public void she_can_learn_the_Language_of(String id) throws Throwable {
    MeritsModel merits = character.getMerits();
    prepareForLanguage(merits, id);
    assertThat(merits.isEntryAllowed(), is(true));
  }

  @Then("^she can not learn the Language of (.*)$")
  public void she_can_not_learn_the_Language_of(String id) throws Throwable {
    MeritsModel merits = character.getMerits();
    prepareForLanguage(merits, id);
    assertThat(merits.isEntryAllowed(), is(false));
  }

  private void prepareForLanguage(MeritsModel merits, String id) {
    MeritOption language = merits.findOptionByReference(new OptionalEntryReference("Language"));
    merits.setSelectedEntryOption(language);
    merits.setCurrentDescription(id);
  }
}