package net.sf.anathema.integration.attributes.points;

import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.hero.traits.model.types.AttributeType.Appearance;
import static net.sf.anathema.hero.traits.model.types.AttributeType.Charisma;
import static net.sf.anathema.hero.traits.model.types.AttributeType.Dexterity;
import static net.sf.anathema.hero.traits.model.types.AttributeType.Intelligence;
import static net.sf.anathema.hero.traits.model.types.AttributeType.Manipulation;
import static net.sf.anathema.hero.traits.model.types.AttributeType.Perception;
import static net.sf.anathema.hero.traits.model.types.AttributeType.Stamina;
import static net.sf.anathema.hero.traits.model.types.AttributeType.Strength;
import static net.sf.anathema.hero.traits.model.types.AttributeType.Wits;

public class AttributeFreebiesMap {

  private Map<String, AttributeFreebies> freebiesByCharacterType = new HashMap<>();

  public AttributeFreebiesMap() {
    freebiesByCharacterType.put("Solar", new AttributeFreebies().withPrimary(8).withSecondary(6).withTertiary(4));
    freebiesByCharacterType.put("Mortal", new AttributeFreebies().withPrimary(6).withSecondary(4).withTertiary(3));
  }

  public void spendAllFreebies(String type, IntegrationAttributes attributes) {
    AttributeFreebies freebies = freebiesByCharacterType.get(type);
    spendOnPrimary(attributes, freebies.primary);
    spendOnSecondary(attributes, freebies.secondary);
    spendOnTertiary(attributes, freebies.tertiary);
  }

  public void spentADotOnPrimary(IntegrationAttributes attributes) {
    spendOnPrimary(attributes, 1);
  }

  public void spentADotOnSecondary(IntegrationAttributes attributes) {
    spendOnSecondary(attributes, 1);
  }

  public void spentADotOnTertiary(IntegrationAttributes attributes) {
    spendOnTertiary(attributes, 1);
  }

  private void spendOnTertiary(IntegrationAttributes attributes, int amount) {
    attributes.spendDotsOnAttributes(amount, Intelligence, Wits, Perception);
  }

  private void spendOnSecondary(IntegrationAttributes attributes, int amount) {
    attributes.spendDotsOnAttributes(amount, Charisma, Appearance, Manipulation);
  }

  private void spendOnPrimary(IntegrationAttributes attributes, int amount) {
    attributes.spendDotsOnAttributes(amount, Strength, Stamina, Dexterity);
  }
}
