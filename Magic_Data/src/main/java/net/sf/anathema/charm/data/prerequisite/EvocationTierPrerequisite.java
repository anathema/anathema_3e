package net.sf.anathema.charm.data.prerequisite;

import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.template.evocations.EvocationTier;
import net.sf.anathema.magic.data.attribute.MagicAttributeImpl;

public class EvocationTierPrerequisite implements CharmPrerequisite {
  private final TreeReference tree;
  private final EvocationTier priorTier;
  private final int priorTierQuantity;

  public EvocationTierPrerequisite(TreeReference tree, EvocationTier tier, int priorTierQuantity) {
    this.tree = tree;
    this.priorTier = tier;
    this.priorTierQuantity = priorTierQuantity;
  }

  @Override
  public void process(PrerequisiteProcessor processor) {
    processor.requiresMagicAttributesFromTree(tree, new MagicAttributeImpl(priorTier.toString(), true), priorTierQuantity);
  }

  @Override
  public void accept(PrerequisiteVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean isSpecific() {
    return false;
  }
}