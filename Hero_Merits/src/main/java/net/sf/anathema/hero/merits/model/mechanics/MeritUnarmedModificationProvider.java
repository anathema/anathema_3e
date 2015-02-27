package net.sf.anathema.hero.merits.model.mechanics;

import net.sf.anathema.equipment.character.UnarmedModificationProvider;
import net.sf.anathema.equipment.stats.WeaponTag;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;

public class MeritUnarmedModificationProvider implements UnarmedModificationProvider {

	private final MeritsModel model;
	
	public MeritUnarmedModificationProvider(MeritsModel model) {
		this.model = model;
	}

	@Override
	public boolean hasModificationOnUnarmed(WeaponTag tag) {
		boolean[] result = new boolean[1];
		result[0] = false;
		for (Merit merit : model.getPossessedEntries()) {
			for (MeritMechanicalDetail detail : merit.getBaseOption().getMechanics()) {
				detail.accept(new EmptyMeritMechanicalDetailVisitor() {

					@Override
					public void visitUnarmedModificationDetail(MeritUnarmedModificationDetail detail) {
						if (detail.hasTransformationForTag(merit.getCurrentValue(), tag)) {
							result[0] = true;
						}
					}					
				});
			}
		}
		return result[0];
	}

	@Override
	public boolean hasModificationOnSavage(WeaponTag tag) {
		boolean[] result = new boolean[1];
		result[0] = false;
		for (Merit merit : model.getPossessedEntries()) {
			for (MeritMechanicalDetail detail : merit.getBaseOption().getMechanics()) {
				detail.accept(new EmptyMeritMechanicalDetailVisitor() {

					@Override
					public void visitSavageModificationDetail(MeritSavageModificationDetail detail) {
						if (detail.hasTransformationForTag(merit.getCurrentValue(), tag)) {
							result[0] = true;
						}
					}					
				});
			}
		}
		return result[0];
	}

	@Override
	public WeaponTag performModificationOnUnarmed(WeaponTag tag) {
		WeaponTag[] result = new WeaponTag[1];
		result[0] = null;
		for (Merit merit : model.getPossessedEntries()) {
			for (MeritMechanicalDetail detail : merit.getBaseOption().getMechanics()) {
				detail.accept(new EmptyMeritMechanicalDetailVisitor() {

					@Override
					public void visitUnarmedModificationDetail(MeritUnarmedModificationDetail detail) {
						if (detail.hasTransformationForTag(merit.getCurrentValue(), tag)) {
							result[0] = detail.getTransformationForTag(merit.getCurrentValue(), tag);
						}
					}					
				});
			}
		}
		return result[0];
	}

	@Override
	public WeaponTag performModificationOnSavage(WeaponTag tag) {
		WeaponTag[] result = new WeaponTag[1];
		result[0] = null;
		for (Merit merit : model.getPossessedEntries()) {
			for (MeritMechanicalDetail detail : merit.getBaseOption().getMechanics()) {
				detail.accept(new EmptyMeritMechanicalDetailVisitor() {

					@Override
					public void visitSavageModificationDetail(MeritSavageModificationDetail detail) {
						if (detail.hasTransformationForTag(merit.getCurrentValue(), tag)) {
							result[0] = detail.getTransformationForTag(merit.getCurrentValue(), tag);
						}
					}					
				});
			}
		}
		return result[0];
	}

}
