package net.sf.anathema.integration.attributes;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import net.sf.anathema.CharacterHolder;
import net.sf.anathema.hero.traits.model.DefaultTraitType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.integration.attributes.points.AttributeFreebiesMap;
import net.sf.anathema.integration.attributes.points.IntegrationAttributes;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Appearance;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Attributes;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Charisma;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Dexterity;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Intelligence;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Manipulation;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Perception;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Stamina;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Strength;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Wits;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@ScenarioScoped
public class AttributeSteps {

  public static final TraitType ANY_ATTRIBUTE_TYPE = Dexterity;
  private final IntegrationAttributes attributes;
  private CharacterHolder character;

  @Inject
  public AttributeSteps(CharacterHolder character) {
    this.character = character;
    this.attributes = new IntegrationAttributes(character);
  }

  @When("^I set any of her attributes to (\\d+)$")
  public void setAnyOfHerAttributesTo(int value) throws Throwable {
    attributes.getAttribute(ANY_ATTRIBUTE_TYPE).setCurrentValue(value);
  }

  @And("^I set the attribute to (\\d+)$")
  public void setTheAttributeTo(int value) throws Throwable {
    setAnyOfHerAttributesTo(value);
  }

  @Then("^she has (\\d+) dots in attribute (.*)$")
  public void she_has_dots_in_attribute(int value, String traitId) throws Throwable {
    DefaultTraitType type = new DefaultTraitType(traitId);
    assertThatAttributeHasValueOf(type, value);
  }

  @Then("^she has (\\d+) dots in the attribute$")
  public void she_has_dots_in_the_attribute(int value) throws Throwable {
    assertThatAttributeHasValueOf(ANY_ATTRIBUTE_TYPE, value);
  }

  @Then("^she has (\\d+) dots in all her attributes$")
  public void she_has_dots_in_all_her_attributes(int value) throws Throwable {
    for (TraitType type : Attributes) {
      assertThatAttributeHasValueOf(type, value);
    }
  }

  @When("^she spends all her Attribute Freebies$")
  public void she_spends_all_attribute_freebies() {
    String type = character.getHero().getSplat().getTemplateType().getHeroType().getId();
    new AttributeFreebiesMap().spendAllFreebies(type, attributes);
  }

  @When("^she spends one additional dot in Primary Attributes$")
  public void she_spends_one_additional_dot_in_Primary_Attributes() throws Throwable {
    new AttributeFreebiesMap().spentADotOnPrimary(attributes);
  }

  @When("^she spends one additional dot in Secondary Attributes$")
  public void she_spends_one_additional_dot_in_Secondary_Attributes() throws Throwable {
    new AttributeFreebiesMap().spentADotOnSecondary(attributes);
  }

  @When("^she spends one additional dot in Tertiary Attributes$")
  public void she_spends_one_additional_dot_in_Tertiary_Attributes() throws Throwable {
    new AttributeFreebiesMap().spentADotOnTertiary(attributes);
  }

  @When("^she spends (\\d+) points on Mental Attributes$")
  public void she_spends_points_on_Mental_Attributes(int amount) throws Throwable {
    attributes.spendDotsOnAttributes(amount, Intelligence, Wits, Perception);
  }

  @When("^she spends (\\d+) points on Social Attributes$")
  public void she_spends_points_on_Social_Attributes(int amount) throws Throwable {
    attributes.spendDotsOnAttributes(amount, Appearance, Charisma, Manipulation);
  }

  @When("^she spends (\\d+) points on Physical Attributes$")
  public void she_spends_points_on_Physical_Attributes(int amount) throws Throwable {
    attributes.spendDotsOnAttributes(amount, Strength, Dexterity, Stamina);
  }

  private void assertThatAttributeHasValueOf(TraitType type, int value) {
    assertThat("Attribute type " + type, attributes.getAttribute(type).getCurrentValue(), is(value));
  }
}
