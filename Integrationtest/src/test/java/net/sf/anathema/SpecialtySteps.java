package net.sf.anathema;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.specialties.model.SpecialtyTypeImpl;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;

import com.google.inject.Inject;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ScenarioScoped
public class SpecialtySteps {

  private final CharacterHolder character;


  @Inject
  public SpecialtySteps(CharacterHolder character) {
    this.character = character;
  }
  
  @When("^she learns a Specialty in (.*)$")
  public void she_learn_a_specialty_in(String id) throws Throwable {
  	SpecialtiesModel model = SpecialtiesModelFetcher.fetch(character.getHero());
  	model.setCurrentDescription("Test");
  	model.setSelectedEntryOption(new SpecialtyTypeImpl(AbilityType.valueOf(id)));
  	boolean learned = model.commitSelection() != null;
    assertThat(learned, is(true));
  }
  
  @When("^she forgets a Specialty in (.*)$")
  public void she_forgets_a_specialty_in(String id) throws Throwable {
  	SpecialtiesModel model = SpecialtiesModelFetcher.fetch(character.getHero());
  	TraitType ability = AbilityType.valueOf(id);
  	Specialty toForget = model.getEntries().stream().filter(specialty -> specialty.getBasicTraitType().equals(ability))
  		.findFirst().get();
  	if (toForget != null) {
  		model.removeEntry(toForget);
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
