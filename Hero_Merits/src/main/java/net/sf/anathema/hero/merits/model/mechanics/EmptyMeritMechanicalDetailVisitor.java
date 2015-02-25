package net.sf.anathema.hero.merits.model.mechanics;

public class EmptyMeritMechanicalDetailVisitor implements MeritMechanicalDetailVisitor {

	@Override
	public void visitHealthDetail(MeritHealthDetail detail) {
		// nothing to do
	}

	@Override
	public void visitPainToleranceDetail(MeritPainToleranceDetail detail) {
		// nothing to do
	}

	@Override
	public void visitUnarmedModificationDetail(
			MeritUnarmedModificationDetail detail) {
		// nothing to do
	}

	@Override
	public void visitSavageModificationDetail(MeritSavageModificationDetail detail) {
		// nothing to do
	}

	@Override
	public void visitExaltedHealingDetail(MeritExaltedHealingDetail detail) {
		// nothing to do
	}

	@Override
	public void visitThaumaturgyDetail(MeritThaumaturgyDetail detail) {
		// nothing to do
	}

}
