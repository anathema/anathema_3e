package net.sf.anathema.character.equipment.creation.model;

import org.junit.Test;

import static net.sf.anathema.character.equipment.creation.model.WeaponTag.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WeaponTagsModel_Test {
  WeaponTagsModel model = new WeaponTagsModel();

  @Test
  public void aWeaponCanOnlyHaveOneSize() throws Exception {
    select(Medium);
    assertTagIsForbidden(Heavy);
  }

  @Test
  public void allSizesAreAvailableWhenOneBecomesDeselected() throws Exception {
    select(Medium);
    deselect(Medium);
    assertTagIsAllowed(Heavy);
  }

  @Test
  public void aWeaponCanOnlyHaveOneDamageType() throws Exception {
    select(Lethal);
    assertTagIsForbidden(Bashing);
  }

  @Test
  public void aWeaponCanOnlyHaveOneRangedType() throws Exception {
    select(Archery);
    assertTagIsForbidden(Thrown);
  }

  @Test
  public void aWeaponCanOnlyHaveOneRange() throws Exception {
    select(MediumRange);
    assertTagIsForbidden(ShortRange);
    assertTagIsForbidden(CloseRange);
    assertTagIsForbidden(LongRange);
  }

  @Test
  public void closeCombatWeaponsCantUseFlame() throws Exception {
    assertTagIsForbidden(Flame);
  }

  @Test
  public void rangedWeaponsCanUseFlame() throws Exception {
    select(Archery);
    assertTagIsAllowed(Flame);
  }

  private void select(WeaponTag tag) {
    model.getSelectedModel(tag).setValue(true);
  }

  private void deselect(WeaponTag tag) {
    model.getSelectedModel(tag).setValue(false);
  }

  private void assertTagIsAllowed(WeaponTag tag) {
    assertThat(model.getEnabledModel(tag).getValue(), is(true));
  }

  private void assertTagIsForbidden(WeaponTag tag) {
    assertThat(model.getEnabledModel(tag).getValue(), is(false));
  }
}