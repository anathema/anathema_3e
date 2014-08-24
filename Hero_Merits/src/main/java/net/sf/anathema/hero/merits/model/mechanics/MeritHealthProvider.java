package net.sf.anathema.hero.merits.model.mechanics;

import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.health.model.IHealthLevelProvider;
import net.sf.anathema.hero.health.model.IPainToleranceProvider;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;

public class MeritHealthProvider implements IHealthLevelProvider, IPainToleranceProvider {
	
	private final MeritsModel merits;
	
	public MeritHealthProvider(MeritsModel merits) {
		this.merits = merits;
	}
	
	@Override
	public int getHealthLevelTypeCount(HealthLevelType type) {
		final int[] levelsOfType = new int[1];
		levelsOfType[0] = 0;
		
		for (Merit merit : merits.getMerits()) {
			for (MeritMechanicalDetail detail : merit.getBaseOption().getMechanics()) {
				detail.accept(new MeritMechanicalDetailVisitor() {

					@Override
					public void visitHealthDetail(MeritHealthDetail detail) {
						for (HealthLevelType providedType : detail.getHealthLevels()) {
							if (providedType == type) {
								levelsOfType[0]++;
							}
						}
					}

					@Override
					public void visitPainToleranceDetail(MeritPainToleranceDetail detail) {
						// TODO Auto-generated method stub
						
					}
					
				});
			}
		}
		
		return levelsOfType[0];
	}

	@Override
	public int getPainToleranceLevel() {
		final int[] tolerance = new int[1];
		tolerance[0] = 0;
		
		for (Merit merit : merits.getMerits()) {
			for (MeritMechanicalDetail detail : merit.getBaseOption().getMechanics()) {
				detail.accept(new MeritMechanicalDetailVisitor() {

					@Override
					public void visitHealthDetail(MeritHealthDetail detail) {
					}

					@Override
					public void visitPainToleranceDetail(MeritPainToleranceDetail detail) {
						tolerance[0] += detail.getTolerance();
					}
					
				});
			}
		}
		
		return tolerance[0];
	}

}
