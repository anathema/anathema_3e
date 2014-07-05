package net.sf.anathema.library.number;

import org.junit.Assert;
import org.junit.Test;

public class RangeTest {

  @Test
  public void testBasics() {
    Range range = Range.bounded(2, 7);
    Assert.assertEquals("lowerBound is 2", range.getLowerBound(), 2);
    Assert.assertEquals("upperBound is 7", range.getUpperBound(), 7);
    Assert.assertEquals("width is 6", range.getWidth(), 6);
  }

  @Test
  public void testContainsInteger() {
    Range range = Range.bounded(2, 7);
    Assert.assertTrue("2 is within", range.contains(2));
    Assert.assertTrue("7 is within", range.contains(7));
    Assert.assertTrue("1 is not within", !range.contains(1));
    Assert.assertTrue("8 is not within", !range.contains(8));
  }

  @Test
  public void testContainsRange() {
    Range range = Range.bounded(2, 7);
    Range inner = Range.bounded(3, 4);
    Range outer = Range.bounded(1, 4);
    Assert.assertTrue("Inner-Range: ", range.contains(inner));
    Assert.assertTrue("Outer-Range: ", !range.contains(outer));
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(Range.bounded(1, 1), Range.bounded(1, 1));
  }

  @Test
  public void testEqualsWithNonRange() {
    Assert.assertTrue(!(Range.bounded(1, 1).equals(new Object())));
  }

  @Test
  public void testOtherEquals() {
    Assert.assertEquals(Range.bounded(2, 5), Range.bounded(2, 5));
  }

  @Test
  public void testOtherToString() {
    Assert.assertEquals("[2,3]", Range.bounded(2, 3).toString());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("[1,2]", Range.bounded(1, 2).toString());
  }
}