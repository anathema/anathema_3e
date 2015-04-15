package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.specialties.model.SpecialtyTypeImpl;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityTraitType;

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
  	model.setSelectedEntryOption(new SpecialtyTypeImpl(new AbilityTraitType(id), null));
  	boolean learned = model.commitSelection() != null;
    assertThat(learned, is(true));
  }
  
  @When("^she forgets a Specialty in (.*)$")
  public void she_forgets_a_specialty_in(String id) throws Throwable {
  	SpecialtiesModel model = SpecialtiesModelFetcher.fetch(character.getHero());
  	TraitType ability = new AbilityTraitType(id);
  	Specialty toForget = model.getEntries().stream().filter(specialty -> specialty.getBasicTraitType().equals(ability))
  		.findFirst().get();
  	if (toForget != null) {
  		model.forget(toForget);
  	}
  	boolean success = toForget != null;
    assertThat(success, is(true));
  }

  @Then("^she can learn a Specialty in (.*)$")
  public void she_can_learn_a_Specialty_in(String id) throws Throwable {
  	SpecialtiesModel model = SpecialtiesModelFetcher.fetch(character.getHero());
    boolean learnable = model.getAllEligibleParentTraits().contains(new AbilityTraitType(id));
    assertThat(learnable, is(true));
  }

  @Then("^she can not learn a Specialty in (.*)$")
  public void she_can_not_learn_a_Specialty_in(String id) throws Throwable {
  	SpecialtiesModel model = SpecialtiesModelFetcher.fetch(character.getHero());
    boolean learnable = model.getAllEligibleParentTraits().contains(new AbilityTraitType(id));
    assertThat(learnable, is(false));
  }
  
  @Then("^she knows (\\d+) Specialties in (.*)$")
  public void she_knows_specialties_in(int count, String id) throws Throwable {
  	SpecialtiesModel model = SpecialtiesModelFetcher.fetch(character.getHero());
  	List<Specialty> specialtiesOfType = model.getAllSpecialtiesOfType(new AbilityTraitType(id));
    boolean countMatches = specialtiesOfType.size() == count;
    assertThat(countMatches, is(true));
  }
  
  
}
