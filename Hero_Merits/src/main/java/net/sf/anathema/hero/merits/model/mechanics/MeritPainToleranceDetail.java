package net.sf.anathema.hero.merits.model.mechanics;

public class MeritPainToleranceDetail implements MeritMechanicalDetail {

	private final int tolerance;
	
	public MeritPainToleranceDetail(int tolerance) {
		this.tolerance = tolerance;
	}
	
	@Override
	public void accept(MeritMechanicalDetailVisitor visitor) {
		visitor.visitPainToleranceDetail(this);
	}

	public int getTolerance() {
		return tolerance;
	}

}
