package net.sf.anathema.hero.merits.model.mechanics;

public interface MeritMechanicalDetail {
	void accept(MeritMechanicalDetailVisitor visitor);
}
