package net.sf.anathema.character.equipment.impl.creation.model.impl.character.model.natural;

import net.sf.anathema.hero.equipment.model.natural.DefaultNaturalSoak;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NaturalSoakTest {

  @Test
  public void soakEqualsStamina() {
    Trait trait = mock(Trait.class);
    when(trait.getType()).thenReturn(AttributeType.Stamina);
    when(trait.getCurrentValue()).thenReturn(2);
    DefaultNaturalSoak naturalSoak = new DefaultNaturalSoak(trait);
    Assert.assertEquals(new Integer(2), naturalSoak.getSoak());
  }
}