package net.sf.anathema.graph.nodes;

import java.util.Optional;

public class WeightedNode implements Comparable<WeightedNode> {

  private final ISimpleNode node;
  private final Double weight;

  public ISimpleNode getNode() {
    return node;
  }

  public Optional<Double> getWeight() {
    return Optional.ofNullable(weight);
  }

  public WeightedNode(ISimpleNode node, Double weight) {
    this.node = node;
    this.weight = weight;
  }

  @Override
  public String toString() {
    return node + "(" + weight + ")";
  }

  public boolean hasSameWeightAs(WeightedNode node) {
    if (weight == null || node.weight == null) {
      return false;
    }
    return weight.equals(node.weight);
  }

  @Override
  public int compareTo(WeightedNode node) {
    if (weight == null || node.weight == null) {
      return 0;
    }
    return (int) (weight - node.weight);
  }
}