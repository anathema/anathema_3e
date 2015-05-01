package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import net.sf.anathema.hero.individual.change.ChangeFlavor;
import net.sf.anathema.points.model.ExperiencePointManagement;
import net.sf.anathema.points.model.PointModelFetcher;
import net.sf.anathema.points.model.overview.IOverviewModelVisitor;
import net.sf.anathema.points.model.overview.IValueModel;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;
import net.sf.anathema.points.model.xp.ExperiencePoints;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ScenarioScoped
public class ExperiencePointSteps {

  private final CharacterHolder character;

  @Inject
  public ExperiencePointSteps(CharacterHolder character) {
    this.character = character;
  }

  @When("^she gains (\\d+) experience points$")
  public void she_gains_experience_points(int amount) throws Throwable {
    ExperiencePoints experiencePoints = PointModelFetcher.fetch(character.getHero()).getExperiencePoints();
    ExperiencePointEntry entry = experiencePoints.addEntry();
    experiencePoints.selectForChange(entry);
    experiencePoints.updateCurrentSelection("Test", amount);
  }

  @When("^she spends (\\d+) experience points$")
  public void she_spends_xp_experience_points(int amount) throws Throwable {
    PointModelFetcher.fetch(character.getHero()).addToExperienceOverview(new ConfigurableValueModel(amount));
    character.getHero().getChangeAnnouncer().announceChangeFlavor(ChangeFlavor.UNSPECIFIED);
  }

  @Then("^she has spent (\\d+) xp points$")
  public void she_has_spent_experience_points(int amount) throws Throwable {
    ExperiencePointManagement pointManagement = PointModelFetcher.fetch(character.getHero()).getExperiencePointManagement();
    int spentPoints = pointManagement.getTotalCosts();
    assertThat(spentPoints, is(amount));
  }

  @Then("^she has (\\d+) experience points$")
  public void she_has_experience_points(int amount) throws Throwable {
    ExperiencePoints experiencePoints = PointModelFetcher.fetch(character.getHero()).getExperiencePoints();
    assertThat(experiencePoints.getTotalExperiencePoints(), is(amount));
  }

  private static class ConfigurableValueModel implements IValueModel<Integer> {
    private final int amount;

    public ConfigurableValueModel(int amount) {
      this.amount = amount;
    }

    @Override
    public Integer getValue() {
      return amount;
    }

    @Override
    public void accept(IOverviewModelVisitor visitor) {

    }

    @Override
    public String getCategoryId() {
      return null;
    }

    @Override
    public String getId() {
      return null;
    }
  }
}
