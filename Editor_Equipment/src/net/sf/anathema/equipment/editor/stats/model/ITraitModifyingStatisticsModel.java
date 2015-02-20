package net.sf.anathema.equipment.editor.stats.model;


public interface ITraitModifyingStatisticsModel extends IEquipmentStatisticsModel
{
	IIntValueModel getDDVModel();
	
	IIntValueModel getPDVModel();
	
	IIntValueModel getMeleeWeaponAccuracyModel();
	
	IIntValueModel getMeleeWeaponDamageModel();
	
	IIntValueModel getRangedWeaponAccuracyModel();
	
	IIntValueModel getRangedWeaponDamageModel();
	
	IIntValueModel getJoinBattleModel();
}
