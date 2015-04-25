package net.sf.anathema;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.hero.traits.model.types.CommonTraitTypes;
import net.sf.anathema.library.model.OptionalEntryReference;
import net.sf.anathema.points.model.overview.IValueModel;

import com.google.inject.Inject;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class MeritSteps {

  private final CharacterHolder character;

  private final BonusModelFetcher bonusModel;

  @Inject
  public MeritSteps(CharacterHolder character) {
    this.character = character;
    this.bonusModel = new BonusModelFetcher(character);
  }
  
  @When("^she earns the (.*) merit at (\\d+)$")
  public void she_earns_the_merit_at_(String meritId, int rank) throws Throwable {
  	MeritsModel model = MeritsModelFetcher.fetch(character.getHero());
    selectMeritOptionById(meritId, model);
  	Merit merit = model.commitSelection();
  	if (merit != null) {
  		merit.setCurrentValue(rank);
  	}
  	boolean isLearnedAtCorrectValue = merit != null && merit.getCurrentValue() == rank;
    assertThat(isLearnedAtCorrectValue, is(true));
  }

  @When("^she earns the (.*) merit$")
  public void she_earns_the_merit(String id) throws Throwable {
  	MeritsModel model = MeritsModelFetcher.fetch(character.getHero());
    selectMeritOptionById(id, model);
  	Merit merit = model.commitSelection();
  	boolean isLearned = merit != null;
    assertThat(isLearned, is(true));
  }

  @Then("^she can earn the (.*) merit at (\\d+)$")
  public void she_can_earn_the_merit_at(String id, int rank) throws Throwable {
    MeritOption matchingOption = findMerit(id);
  	boolean canEarn = matchingOption.isLegalValue(rank);
    assertThat(canEarn, is(true));
  }

  @Then("^she can earn the (.*) merit$")
  public void she_can_earn_the_merit(String id) throws Throwable {
    MeritOption matchingOption = findMerit(id);
    MeritsModel model = MeritsModelFetcher.fetch(character.getHero());
    boolean canEarn = model.isAllowedOption(matchingOption);
    assertThat(canEarn, is(true));
  }

  @Then("^she can not earn the (.*) merit$")
  public void she_can_not_earn_the_merit(String id) throws Throwable {
    MeritOption matchingOption = findMerit(id);
    MeritsModel model = MeritsModelFetcher.fetch(character.getHero());
    boolean canEarn = model.isAllowedOption(matchingOption);
    assertThat(canEarn, is(false));
  }

  @Then("^she has spent (\\d+) merit points$")
  public void she_has_merit_points(int points) throws Throwable {
  	IValueModel<Integer> model = bonusModel.findBonusModel(MeritsModel.ID.toString(), MeritsModel.ID.toString());
  	assertThat(model.getValue() == points, is(true));
  }

  private void selectMeritOptionById(String meritId, MeritsModel model) {
    MeritOption option = model.findOptionByReference(new OptionalEntryReference(meritId));
    model.setSelectedEntryOption(option);
  }

  private MeritOption findMerit(String id) {
    MeritsModel model = MeritsModelFetcher.fetch(character.getHero());
    return model.getAllEntryOptions().stream()
            .filter(option -> option.getTraitType().getId().equals(id)).findAny().get();
  }

  @And("^she is not a Martial Artist$")
  public void she_is_not_a_Martial_Artist() throws Throwable {
    //nothing to do
  }

  @And("^she is a Martial Artist$")
  public void she_is_a_Martial_Artist() throws Throwable {
    AbilitiesModelFetcher.fetch(character.getHero()).getTrait(CommonTraitTypes.Brawl).setCurrentValue(1);
    she_earns_the_merit("Martial Artist");
  }
}