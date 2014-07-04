package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.library.model.BooleanValueModel;

public interface IArtifactStatisticsModel extends IEquipmentStatisticsModel
{
	IIntValueModel getAttuneCostModel();
	
	BooleanValueModel getForeignAttunementModel();
	
	BooleanValueModel getRequireAttunementModel();
}
