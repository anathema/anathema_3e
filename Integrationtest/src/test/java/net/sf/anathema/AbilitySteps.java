package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import junit.framework.Assert;
import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.martial.display.MartialArtsModelFetcher;
import net.sf.anathema.hero.martial.model.MartialArtsModel;
import net.sf.anathema.hero.martial.model.StyleName;
import net.sf.anathema.hero.traits.display.Traits;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;
import net.sf.anathema.points.model.overview.SpendingModel;

import static net.sf.anathema.hero.traits.model.state.FavoredTraitStateType.Favored;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

@ScenarioScoped
public class AbilitySteps {

  public static final int ASSUMED_THRESHOLD_FOR_BONUSPOINTS = 3;
  private final CharacterHolder character;

  private final BonusModelFetcher bonusModel;

  @Inject
  public AbilitySteps(CharacterHolder character) {
    this.character = character;
    this.bonusModel = new BonusModelFetcher(character);
  }
  
  @When("^I favor her (.*)$")
  public void favor_her(String abilityName) {
    Trait ability = character.getTraitConfiguration().getTrait(new TraitType(abilityName));
    TraitStateMap stateMap = AbilitiesModelFetcher.fetch(character.getHero());
    stateMap.getState(ability).advanceState();
  }
  
  @When("^I Caste her (.*)$")
  public void caste_her(String abilityName) {
    Trait ability = character.getTraitConfiguration().getTrait(new TraitType(abilityName));
    int currentValue = ability.getCurrentValue();
    TraitStateMap stateMap = AbilitiesModelFetcher.fetch(character.getHero());
    stateMap.getState(ability).advanceState();
    stateMap.getState(ability).advanceState();
    // Awkward workaround for having to traverse through Favored state
    if (currentValue == 0) {
    	ability.setCurrentValue(0);
    }
  }

  @Then("^she has (\\d+) dots in (.*)$")
  public void she_has_dots_in(int amount, String abilityName) throws Throwable {
    Trait ability = character.getTraitConfiguration().getTrait(new TraitType(abilityName));
    assertThat(ability.getCurrentValue(), is(amount));
  }

  @And("^she spends all her general Ability dots$")
  public void she_spends_all_her_Ability_dots() throws Throwable {
    she_exceeds_her_Ability_allotment_by_dot(0);
  }

  @When("^she exceeds her Ability allotment by (\\d+) dot$")
  public void she_exceeds_her_Ability_allotment_by_dot(int overspending) throws Throwable {
    int pointsToSpend = determinePointsToSpend(overspending, "Abilities", "General");
    spendPoints(pointsToSpend);
  }

  private int determinePointsToSpend(int overspending, String category, String id) {
    SpendingModel model = (SpendingModel) bonusModel.findBonusModel(category, id);
    int freeAbilityPoints = model.getAllotment();
    return freeAbilityPoints + overspending;
  }

  private void spendPoints(int pointsToSpend) {
    for (;pointsToSpend>0; pointsToSpend--){
      spendAPoint();
    }
  }

  private void spendAPoint() {
    AbilitiesModel abilitiesModel = AbilitiesModelFetcher.fetch(character.getHero());
    Traits traits = character.getTraitConfiguration().getAll();
    for (Trait trait : traits) {
      boolean isAbility = abilitiesModel.contains(trait.getType());
      boolean hasNotYetReachedThreshold = trait.getCreationValue() < ASSUMED_THRESHOLD_FOR_BONUSPOINTS;
      if (isAbility && hasNotYetReachedThreshold && abilitiesModel.getState(trait) != Favored) {
        increaseTraitValueByOne(trait);
        break;
      }
    }
  }

  private void increaseTraitValueByOne(Trait trait) {
    trait.setCreationValue(trait.getCreationValue() + 1);
  }

  @And("^she cannot learn (.*) martial arts$")
  public void she_cannot_learn_martialarts (String style) throws Throwable {
    MartialArtsModel martialArtsModel = MartialArtsModelFetcher.fetch(character.getHero());
    martialArtsModel.selectStyle(new StyleName(style));
    Trait selectedStyle = martialArtsModel.getSelectedStyle();
    martialArtsModel.learnSelectedStyle();
    assertFalse(martialArtsModel.getLearnedStyles().contains(selectedStyle));
  }
}
