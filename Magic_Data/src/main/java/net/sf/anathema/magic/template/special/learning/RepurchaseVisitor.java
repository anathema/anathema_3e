package net.sf.anathema.magic.template.special.learning;

public interface RepurchaseVisitor {
	void visitTraitRepurchase(TraitRepurchase repurchase);
	
	void visitTierRepurchase(TierRepurchase repurchase);
	
	void visitStaticRepurchase(StaticRepurchase repurchase);
}
