package net.sf.anathema;

import cucumber.api.java.en.Then;
import cucumber.runtime.java.guice.ScenarioScoped;

import net.sf.anathema.points.model.ExperiencePointManagement;
import net.sf.anathema.points.model.PointModelFetcher;

import com.google.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ScenarioScoped
public class ExperiencePointSteps {

  private final CharacterHolder character;

  @Inject
  public ExperiencePointSteps(CharacterHolder character) {
    this.character = character;
  }

  @Then("^she has spent (\\d+) xp points$")
  public void she_has_spent_bonus_points(int amount) throws Throwable {
    ExperiencePointManagement pointManagement = PointModelFetcher.fetch(character.getHero()).getExperiencePointManagement();
    int spentPoints = pointManagement.getTotalCosts();
    assertThat(spentPoints, is(amount));
  }
}
