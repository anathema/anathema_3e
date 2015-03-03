package net.sf.anathema;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;

import com.google.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ScenarioScoped
public class SpellSteps {

  private final CharacterHolder character;

  @Inject
  public SpellSteps(CharacterHolder character) {
    this.character = character;
  }

  @When("^she learns the Spell (.*)$")
  public void she_learns_the_Spell(String id) throws Throwable {
    character.getSpells().addSpells(Spells.singleSpell(character.getSpells().getSpellById(id)));
  }

  @When("^she forgets the Spell (.*)$")
  public void she_forgets_the_Spell(String id) throws Throwable {
  	character.getSpells().removeSpells(Spells.singleSpell(character.getSpells().getSpellById(id)));
  }

  @Then("^she can learn the Spell (.*)$")
  public void she_can_learn_the_Spell(String id) throws Throwable {
    Spell Spell = character.getSpells().getSpellById(id);
    boolean learnable = character.getSpells().isSpellAllowed(Spell);
    assertThat(learnable, is(true));
  }

  @Then("^she can not learn the Spell (.*)$")
  public void she_can_not_learn_the_Spell(String id) throws Throwable {
    Spell Spell = character.getSpells().getSpellById(id);
    boolean learnable = character.getSpells().isSpellAllowed(Spell);
    assertThat(learnable, is(false));
  }

  @Then("^she knows the Spell (.*)$")
  public void she_knows_the_Spell(String id) throws Throwable {
    boolean learned = character.getSpells().getLearnedSpells().asList().contains(character.getSpells().getSpellById(id));
    assertThat(learned, is(true));
  }

  @Then("^she does not know the Spell (.*)$")
  public void she_does_not_know_the_Spell(String id) throws Throwable {
    boolean learned = character.getSpells().getLearnedSpells().asList().contains(character.getSpells().getSpellById(id));
    assertThat(learned, is(false));
  }
}