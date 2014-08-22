package net.sf.anathema;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;

import com.google.inject.Inject;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SpecialtySteps {

  private final CharacterHolder character;

  private final BonusModelFetcher bonusModel;

  @Inject
  public SpecialtySteps(CharacterHolder character) {
    this.character = character;
    this.bonusModel = new BonusModelFetcher(character);
  }
  
  @When("^she learns a Specialty in (.*)$")
  public void she_learn_a_specialty_in(String id) throws Throwable {
  	SpecialtiesModel model = SpecialtiesModelFetcher.fetch(character.getHero());
  	model.setCurrentSpecialtyName("Test");
  	model.setCurrentTrait(AbilityType.valueOf(id));
  	boolean learned = model.commitSelection();
    assertThat(learned, is(true));
  }
  
  @When("^she forgets a Specialty in (.*)$")
  public void she_forgets_a_specialty_in(String id) throws Throwable {
  	SpecialtiesModel model = SpecialtiesModelFetcher.fetch(character.getHero());
  	TraitType ability = AbilityType.valueOf(id);
  	Specialty toForget = model.getAllSpecialties().stream().filter(specialty -> specialty.getBasicTraitType().equals(ability))
  		.findFirst().get();
  	if (toForget != null) {
  		model.removeSpecialty(toForget);
  	}
  	boolean success = toForget != null;
    assertThat(success, is(true));
  }

  @Then("^she can learn a Specialty in (.*)$")
  public void she_can_learn_a_Specialty_in(String id) throws Throwable {
  	SpecialtiesModel model = SpecialtiesModelFetcher.fetch(character.getHero());
    boolean learnable = model.getAllEligibleParentTraits().contains(AbilityType.valueOf(id));
    assertThat(learnable, is(true));
  }

  @Then("^she can not learn a Specialty in (.*)$")
  public void she_can_not_learn_a_Specialty_in(String id) throws Throwable {
  	SpecialtiesModel model = SpecialtiesModelFetcher.fetch(character.getHero());
    boolean learnable = model.getAllEligibleParentTraits().contains(AbilityType.valueOf(id));
    assertThat(learnable, is(false));
  }
  
  @Then("^she knows (\\d+) Specialties in (.*)$")
  public void she_knows_specialties_in(int count, String id) throws Throwable {
  	SpecialtiesModel model = SpecialtiesModelFetcher.fetch(character.getHero());
  	List<Specialty> specialtiesOfType = model.getAllSpecialtiesOfType(AbilityType.valueOf(id));
    boolean countMatches = specialtiesOfType.size() == count;
    assertThat(countMatches, is(true));
  }
  
  
}
