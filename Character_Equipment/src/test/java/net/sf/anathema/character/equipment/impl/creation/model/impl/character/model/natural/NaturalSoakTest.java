package net.sf.anathema.character.equipment.impl.creation.model.impl.character.model.natural;

import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.model.types.ValuedTraitType;
import net.sf.anathema.hero.equipment.model.natural.DefaultNaturalSoak;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

// todo (urs) fix me
@Ignore
public class NaturalSoakTest {

  @Test
  public void testSoakForMortals() {
    DefaultNaturalSoak naturalSoak = new DefaultNaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2));
    Assert.assertEquals(Integer.valueOf(0), naturalSoak.getSoak());
    Assert.assertEquals(Integer.valueOf(2), naturalSoak.getSoak());
  }

  @Test
  public void testSoakForExalts() {
    DefaultNaturalSoak naturalSoak = new DefaultNaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2));
    Assert.assertEquals(new Integer(1), naturalSoak.getSoak());
    Assert.assertEquals(new Integer(2), naturalSoak.getSoak());
  }

  @Test
  public void testSoakForEssenceUsers() {
    DefaultNaturalSoak naturalSoak = new DefaultNaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2));
    Assert.assertEquals(new Integer(1), naturalSoak.getSoak());
    Assert.assertEquals(new Integer(2), naturalSoak.getSoak());
  }
}