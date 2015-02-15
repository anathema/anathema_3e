package net.sf.anathema;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import cucumber.api.PendingException;
import net.sf.anathema.charm.data.reference.SpellName;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.hero.merits.model.MeritReference;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import com.google.inject.Inject;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

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
    MeritOption language = merits.findOptionByReference(new MeritReference("Language"));
    merits.setCurrentMeritOption(language);
    merits.setCurrentDescription(id);
  }
}