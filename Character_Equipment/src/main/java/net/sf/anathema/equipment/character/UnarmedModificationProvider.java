package net.sf.anathema.equipment.character;

import net.sf.anathema.equipment.stats.WeaponTag;

public interface UnarmedModificationProvider {
	
	boolean hasModificationOnUnarmed(WeaponTag tag);
	
	boolean hasModificationOnSavage(WeaponTag tag);
	
	WeaponTag performModificationOnUnarmed(WeaponTag tag);
	
	WeaponTag performModificationOnSavage(WeaponTag tag);
}
