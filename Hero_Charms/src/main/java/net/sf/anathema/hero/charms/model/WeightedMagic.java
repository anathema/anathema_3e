package net.sf.anathema.hero.charms.model;

import net.sf.anathema.magic.data.Magic;

import com.google.common.base.Objects;

public class WeightedMagic implements Comparable<WeightedMagic> {

  private final Magic value;
  private final int weight;

  public WeightedMagic(Magic value, int weight) {
    this.value = value;
    this.weight = weight;
  }

  public int getWeight() {
    return weight;
  }

  public Magic getValue() {
    return value;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof WeightedMagic)) {
      return false;
    }
    WeightedMagic weightedMagic = (WeightedMagic) obj;
    return weight == weightedMagic.getWeight() && Objects.equal(value, weightedMagic.getValue());
  }

  @Override
  public int hashCode() {
    return weight;
  }

  @Override
  public int compareTo(WeightedMagic o) {
    return weight - o.weight;
  }
}