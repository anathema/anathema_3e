package net.sf.anathema.charm.template.special;

import net.sf.anathema.platform.persistence.JsonField;

@JsonField("type")
public interface Repurchase {
	void visit(RepurchaseVisitor visitor);
}
