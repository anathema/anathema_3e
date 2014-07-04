package net.sf.anathema.library;

import net.sf.anathema.library.number.IntegerRange;
import org.junit.Assert;
import org.junit.Test;

public class IntegerRangeTest {

  @Test
  public void testBasics() {
    IntegerRange range = new IntegerRange(2, 7);
    Assert.assertEquals("lowerBound is 2", range.getLowerBound(), 2);
    Assert.assertEquals("upperBound is 7", range.getUpperBound(), 7);
    Assert.assertEquals("width is 6", range.getWidth(), 6);
  }

  @Test
  public void testContainsInteger() {
    IntegerRange range = new IntegerRange(2, 7);
    Assert.assertTrue("2 is within", range.contains(2));
    Assert.assertTrue("7 is within", range.contains(7));
    Assert.assertTrue("1 is not within", !range.contains(1));
    Assert.assertTrue("8 is not within", !range.contains(8));
  }

  @Test
  public void testContainsRange() {
    IntegerRange range = new IntegerRange(2, 7);
    IntegerRange inner = new IntegerRange(3, 4);
    IntegerRange outer = new IntegerRange(1, 4);
    Assert.assertTrue("Inner-Range: ", range.contains(inner));
    Assert.assertTrue("Outer-Range: ", !range.contains(outer));
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(new IntegerRange(1, 1), new IntegerRange(1, 1));
  }

  @Test
  public void testEqualsWithNonRange() {
    Assert.assertTrue(!(new IntegerRange(1, 1).equals(new Object())));
  }

  @Test
  public void testOtherEquals() {
    Assert.assertEquals(new IntegerRange(2, 5), new IntegerRange(2, 5));
  }

  @Test
  public void testOtherToString() {
    Assert.assertEquals("[2,3]", new IntegerRange(2, 3).toString());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("[1,2]", new IntegerRange(1, 2).toString());
  }
}