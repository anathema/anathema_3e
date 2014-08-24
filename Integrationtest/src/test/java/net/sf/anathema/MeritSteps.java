package net.sf.anathema;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.points.model.overview.IValueModel;

import com.google.inject.Inject;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

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
  	model.setCurrentMerit(meritId);
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
  	model.setCurrentMerit(id);
  	Merit merit = model.commitSelection();
  	boolean isLearned = merit != null;
    assertThat(isLearned, is(true));
  }
  
  @Then("^she can earn the (.*) merit at (\\d+)$")
  public void she_can_earn_the_merit_at(String id, int rank) throws Throwable {
  	MeritsModel model = MeritsModelFetcher.fetch(character.getHero());
  	MeritOption matchingOption = model.getCurrentMeritOptions().stream()
  			.filter(option -> option.getId().equals(id)).findAny().get();
  	boolean canEarn = matchingOption != null && matchingOption.isLegalValue(rank);
    assertThat(canEarn, is(true));
  }
  
  @Then("^she can earn the (.*) merit$")
  public void she_can_earn_the_merit(String id) throws Throwable {
  	MeritsModel model = MeritsModelFetcher.fetch(character.getHero());
  	boolean canEarn = model.getCurrentMeritOptionsOfAllTypes().stream()
  			.filter(option -> option.getId().equals(id)).count() != 0;
    assertThat(canEarn, is(true));
  }
  
  @Then("^she can not earn the (.*) merit$")
  public void she_can_not_earn_the_merit(String id) throws Throwable {
  	MeritsModel model = MeritsModelFetcher.fetch(character.getHero());
  	boolean canEarn = model.getCurrentMeritOptions().stream()
  			.filter(option -> option.getId().equals(id)).count() != 0;
    assertThat(canEarn, is(false));
  }
  
  @Then("^she has spent (\\d+) merit points$")
  public void she_has_merit_points(int points) throws Throwable {
  	IValueModel<Integer> model = bonusModel.findBonusModel(MeritsModel.ID.toString(), MeritsModel.ID.toString());
  	assertThat(model.getValue() == points, is(true));
  }
  
  
}
