package net.sf.anathema.library.number;

public class Range {
  public static Range unbounded() {
    return new Range(Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public static Range boundedFromBelow(int lowerBound) {
    return new Range(lowerBound, Integer.MAX_VALUE);
  }

  public static Range boundedFromAbove(int upperBound) {
    return new Range(Integer.MIN_VALUE, upperBound);
  }

  public static Range bounded(int lowerBound, int upperBound) {
    return new Range(lowerBound, upperBound);
  }

  private int high;
  private int low;

  private Range(int low, int high) {
    this.low = low;
    this.high = high;
  }

  public boolean contains(int value) {
    return low <= value && high >= value;
  }

  public boolean contains(Range range) {
    return range.getLowerBound() >= low && range.getUpperBound() <= high;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Range)) {
      return false;
    }
    Range range = (Range) object;
    return range.getLowerBound() == low && range.getUpperBound() == high;
  }

  @Override
  public int hashCode() {
    return getLowerBound() + 7 * getUpperBound();
  }

  public int getLowerBound() {
    return low;
  }

  public int getUpperBound() {
    return high;
  }

  public int getWidth() {
    return high - low + 1;
  }

  @Override
  public String toString() {
    return "[" + low + "," + high + "]";
  }

  public int getNearestValue(int value) {
    if (value < getLowerBound()) {
      return getLowerBound();
    }
    if (value > getUpperBound()) {
      return getUpperBound();
    }
    return value;
  }
}