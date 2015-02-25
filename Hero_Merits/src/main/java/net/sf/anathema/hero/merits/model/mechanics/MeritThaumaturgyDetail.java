package net.sf.anathema.hero.merits.model.mechanics;

public class MeritThaumaturgyDetail implements MeritMechanicalDetail {

	@Override
	public void accept(MeritMechanicalDetailVisitor visitor) {
		visitor.visitThaumaturgyDetail(this);
	}

}
