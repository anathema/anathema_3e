package net.sf.anathema.hero.merits.model.mechanics;

public interface MeritMechanicalDetailVisitor {
	void visitHealthDetail(MeritHealthDetail detail);
	
	void visitPainToleranceDetail(MeritPainToleranceDetail detail);
	
	void visitUnarmedModificationDetail(MeritUnarmedModificationDetail detail);
	
	void visitSavageModificationDetail(MeritSavageModificationDetail detail);
}
