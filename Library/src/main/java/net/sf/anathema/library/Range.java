package net.sf.anathema.library;

public class Range {
  private int high;
  private int low;

  public Range(Integer low, Integer high) {
    this.low = low == null ? Integer.MIN_VALUE : low;
    this.high = high == null ? Integer.MAX_VALUE : high;
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