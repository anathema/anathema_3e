package net.sf.anathema.hero.equipment.model.merits;

import net.sf.anathema.equipment.character.UnarmedModificationProvider;
import net.sf.anathema.equipment.stats.WeaponTag;
import net.sf.anathema.hero.merits.model.MechanicalDetailReference;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;

public class MeritUnarmedModificationProvider implements UnarmedModificationProvider {

  private final MeritsModel model;

  public MeritUnarmedModificationProvider(MeritsModel model) {
    this.model = model;
  }

  @Override
  public boolean hasModificationOnUnarmed(WeaponTag tag) {
    MechanicalDetailReference addsUnarmedModification = new MechanicalDetailReference("AddsUnarmedModification");
    return hasModification(tag, addsUnarmedModification);
  }

  @Override
  public boolean hasModificationOnSavage(WeaponTag tag) {
    MechanicalDetailReference addsSavageModification = new MechanicalDetailReference("AddSavageModification");
    return hasModification(tag, addsSavageModification);
  }

  @Override
  public WeaponTag performModificationOnUnarmed(WeaponTag tag) {
    MechanicalDetailReference addsUnarmedModification = new MechanicalDetailReference("AddsUnarmedModification");
    return performModification(tag, addsUnarmedModification);
  }

  @Override
  public WeaponTag performModificationOnSavage(WeaponTag tag) {
    MechanicalDetailReference addsSavageModification = new MechanicalDetailReference("AddSavageModification");
    return performModification(tag, addsSavageModification);
  }

  private boolean hasModification(WeaponTag tag, MechanicalDetailReference reference) {
    boolean hasModification = false;
    for (Merit merit : model.getPossessedEntries()) {
      if (!(merit.hasMechanicalDetail(reference))) {
        continue;
      }
      MechanicalDetail detail = merit.getMechanicalDetail(reference);
      hasModification |= new WeaponModificationDetail(detail).hasModification(merit.getCurrentValue(), tag);
    }
    return hasModification;
  }

  //TODO Do we need to return null?
  //TODO Do we need the condition?
  private WeaponTag performModification(WeaponTag tag, MechanicalDetailReference addsUnarmedModification) {
    for (Merit merit : model.getPossessedEntries()) {
      MechanicalDetail detail = merit.getMechanicalDetail(addsUnarmedModification);
      WeaponModificationDetail weaponModificationDetail = new WeaponModificationDetail(detail);
      if (weaponModificationDetail.hasModification(merit.getCurrentValue(), tag)) {
        return weaponModificationDetail.modify(merit.getCurrentValue(), tag);
      }
    }
    return null;
  }
}