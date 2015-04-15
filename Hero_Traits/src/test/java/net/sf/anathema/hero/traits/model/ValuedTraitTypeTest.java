package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.traits.model.types.ValuedTraitType;
import org.junit.Test;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Archery;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Athletics;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValuedTraitTypeTest {

  @SuppressWarnings("EqualsWithItself")
  @Test
  public void testIdenticalObjectEquals() throws Exception {
    ValuedTraitType original = new ValuedTraitType(Archery, 2);
    assertTrue(original.equals(original));
  }

  @SuppressWarnings("EqualsBetweenInconvertibleTypes")
  @Test
  public void testOtherClassDoesntEqual() throws Exception {
    assertFalse(new ValuedTraitType(Archery, 2).equals("Hallo?"));
  }

  @SuppressWarnings("EqualsWithItself")
  @Test
  public void testIdenticalNullObjectsEqual() throws Exception {
    ValuedTraitType original = ValuedTraitType.NULL_TYPE;
    assertTrue(original.equals(original));
  }

  @Test
  public void testEqualObject() throws Exception {
    ValuedTraitType original = new ValuedTraitType(Archery, 2);
    ValuedTraitType copy = new ValuedTraitType(Archery, 2);
    assertTrue(original.equals(copy));
    assertTrue(copy.equals(original));
  }

  @Test
  public void testDifferentType() throws Exception {
    ValuedTraitType original = new ValuedTraitType(Archery, 2);
    ValuedTraitType copy = new ValuedTraitType(Athletics, 2);
    assertFalse(original.equals(copy));
    assertFalse(copy.equals(original));
  }

  @Test
  public void testDifferentValue() throws Exception {
    ValuedTraitType original = new ValuedTraitType(Archery, 2);
    ValuedTraitType copy = new ValuedTraitType(Archery, 3);
    assertFalse(original.equals(copy));
    assertFalse(copy.equals(original));
  }
}