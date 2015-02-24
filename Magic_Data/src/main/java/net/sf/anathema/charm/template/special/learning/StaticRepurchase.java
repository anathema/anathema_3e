package net.sf.anathema.charm.template.special.learning;

import net.sf.anathema.platform.persistence.JsonType;

@JsonType("static")
public class StaticRepurchase implements Repurchase {
  public int limit;

	@Override
	public void visit(RepurchaseVisitor visitor) {
		visitor.visitStaticRepurchase(this);
	}
}
