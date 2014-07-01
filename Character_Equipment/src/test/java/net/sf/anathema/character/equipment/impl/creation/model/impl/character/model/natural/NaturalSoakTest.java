package net.sf.anathema.character.equipment.impl.creation.model.impl.character.model.natural;

import net.sf.anathema.hero.equipment.model.natural.DefaultNaturalSoak;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.model.types.ValuedTraitType;
import org.junit.Assert;
import org.junit.Test;

public class NaturalSoakTest {

  @Test
  public void soakEqualsStamina() {
    DefaultNaturalSoak naturalSoak = new DefaultNaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2));
    Assert.assertEquals(new Integer(2), naturalSoak.getSoak());
  }
}