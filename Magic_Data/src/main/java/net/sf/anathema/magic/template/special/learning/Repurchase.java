package net.sf.anathema.magic.template.special.learning;

import net.sf.anathema.platform.persistence.JsonField;

@JsonField("type")
public interface Repurchase {
	void visit(RepurchaseVisitor visitor);
}
