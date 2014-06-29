package net.sf.anathema.integration.attributes.points;

import net.sf.anathema.CharacterHolder;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.types.AttributeType;

public class IntegrationAttributes {

  private CharacterHolder holder;

  public IntegrationAttributes(CharacterHolder holder) {
    this.holder = holder;
  }

  public Trait getAttribute(AttributeType type) {
    TraitMap traitConfiguration = TraitModelFetcher.fetch(holder.getHero());
    return traitConfiguration.getTrait(type);
  }

  public void spendDotsOnAttributes(int amount, AttributeType... attributeTypes) {
    for (; amount > 0; amount--) {
      for (AttributeType attributeType : attributeTypes) {
        Trait attribute = getAttribute(attributeType);
        if (attribute.getCreationValue() < 5) {
          attribute.setCreationValue(attribute.getCreationValue() + 1);
          break;
        }
      }
    }
  }
}
