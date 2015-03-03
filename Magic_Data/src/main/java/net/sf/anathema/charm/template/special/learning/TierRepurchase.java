package net.sf.anathema.charm.template.special.learning;

import net.sf.anathema.platform.persistence.JsonType;

import java.util.ArrayList;
import java.util.List;

@JsonType("tier")
public class TierRepurchase implements Repurchase {
  public List<Tier> tiers = new ArrayList<>();

	@Override
	public void visit(RepurchaseVisitor visitor) {
		visitor.visitTierRepurchase(this);
	}
}
