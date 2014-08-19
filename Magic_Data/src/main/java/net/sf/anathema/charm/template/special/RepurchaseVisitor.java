package net.sf.anathema.charm.template.special;

public interface RepurchaseVisitor {
	void visitTraitRepurchase(TraitRepurchase repurchase);
	
	void visitTierRepurchase(TierRepurchase repurchase);
	
	void visitStaticRepurchase(StaticRepurchase repurchase);
}
