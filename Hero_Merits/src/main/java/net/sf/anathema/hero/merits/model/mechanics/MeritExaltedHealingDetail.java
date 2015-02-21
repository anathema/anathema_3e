package net.sf.anathema.hero.merits.model.mechanics;

public class MeritExaltedHealingDetail implements MeritMechanicalDetail {

	@Override
	public void accept(MeritMechanicalDetailVisitor visitor) {
		visitor.visitExaltedHealingDetail(this);
	}

}
