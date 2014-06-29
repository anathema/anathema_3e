package net.sf.anathema.integration.attributes;

import com.google.inject.Inject;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.CharacterHolder;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.integration.attributes.points.AttributeFreebies;
import net.sf.anathema.integration.attributes.points.AttributeFreebiesMap;
import net.sf.anathema.integration.attributes.points.IntegrationAttributes;

import static net.sf.anathema.hero.traits.model.types.AttributeType.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AttributeSteps {

  public static final AttributeType ANY_ATTRIBUTE_TYPE = Dexterity;
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
    AttributeType type = valueOf(traitId);
    assertThatAttributeHasValueOf(type, value);
  }

  @Then("^she has (\\d+) dots in the attribute$")
  public void she_has_dots_in_the_attribute(int value) throws Throwable {
    assertThatAttributeHasValueOf(ANY_ATTRIBUTE_TYPE, value);
  }

  @Then("^she has (\\d+) dots in all her attributes$")
  public void she_has_dots_in_all_her_attributes(int value) throws Throwable {
    for (AttributeType type : values()) {
      assertThatAttributeHasValueOf(type, value);
    }
  }

  @When("^she spends all her Attribute Freebies$")
  public void she_spends_all_attribute_freebies() {
    String type = character.getHero().getTemplate().getTemplateType().getCharacterType().getId();
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

  private void assertThatAttributeHasValueOf(AttributeType type, int value) {
    assertThat("Attribute type " + type, attributes.getAttribute(type).getCurrentValue(), is(value));
  }
}
