package net.sf.anathema.hero.merits.model.mechanics;

import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyProvider;

public class MeritThaumaturgyProvider implements ThaumaturgyProvider {
private final MeritsModel merits;
	
	public MeritThaumaturgyProvider(MeritsModel merits) {
		this.merits = merits;
	}
	
	@Override
	public boolean grantsThaumaturgy() {
		final boolean[] access = new boolean[1];
		access[0] = false;
		
		for (Merit merit : merits.getKnownTraits()) {
			for (MeritMechanicalDetail detail : merit.getBaseOption().getMechanics()) {
				detail.accept(new EmptyMeritMechanicalDetailVisitor() {

					@Override
					public void visitThaumaturgyDetail(MeritThaumaturgyDetail detail) {
						access[0] = true;
					}					
				});
			}
		}
		
		return access[0];
	}

	@Override
	public int numberOfRitualsProvided() {
		return 0;
	}
}
