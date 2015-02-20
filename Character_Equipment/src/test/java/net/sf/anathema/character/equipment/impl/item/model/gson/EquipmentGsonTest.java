package net.sf.anathema.character.equipment.impl.item.model.gson;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.sf.anathema.equipment.database.gson.EquipmentGson;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.impl.ArmourStats;
import net.sf.anathema.equipment.stats.impl.ArtifactStats;
import net.sf.anathema.equipment.stats.impl.TraitModifyingStats;
import net.sf.anathema.equipment.stats.impl.WeaponStats;
import net.sf.anathema.equipment.template.EquipmentTemplate;
import net.sf.anathema.equipment.template.IEquipmentTemplate;

import org.junit.Test;

public class EquipmentGsonTest {

  private EquipmentTemplate originalTemplate = new EquipmentTemplate("First Test", "The test used to shape Creation", null);
  private EquipmentGson gson = new EquipmentGson();

  @Test
  public void mentionsTypeInMeleeWeaponStats() throws Exception {
    WeaponStats meleeWeapon = GsonStatMother.createMeleeWeapon();
    String json = serializeWithStats(meleeWeapon);
    assertThat(json.contains("Weapon"), is(true));
  }

  @Test
  public void deserializesEmptyDescriptionToNull() throws Exception {
    EquipmentTemplate template = new EquipmentTemplate("Test", null, null);
    String json = gson.toJson(template);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(readTemplate.getDescription().isEmpty(), is(true));
  }

  @Test
  public void roundTripForMeleeWeapon() throws Exception {
    WeaponStats meleeWeapon = GsonStatMother.createMeleeWeapon();
    String json = serializeWithStats(meleeWeapon);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(gson.toJson(readTemplate), is(json));
  }

  @Test
  public void mentionsTypeInArtifactStats() throws Exception {
    ArtifactStats artifact = GsonStatMother.createArtifact();
    String json = serializeWithStats(artifact);
    assertThat(json.contains("Artifact"), is(true));
  }

  @Test
  public void roundTripForArtifact() throws Exception {
    ArtifactStats artifact = GsonStatMother.createArtifact();
    String json = serializeWithStats(artifact);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(gson.toJson(readTemplate), is(json));
  }

  @Test
  public void mentionsTypeInArmourStats() throws Exception {
    ArmourStats armour = GsonStatMother.createArmour();
    String json = serializeWithStats(armour);
    assertThat(json.contains("Armour"), is(true));
  }

  @Test
  public void roundTripForArmour() throws Exception {
    ArmourStats armour = GsonStatMother.createArmour();
    String json = serializeWithStats(armour);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(gson.toJson(readTemplate), is(json));
  }

  @Test
  public void mentionsTypeInTraitModifier() throws Exception {
    TraitModifyingStats modifier = GsonStatMother.createTraitModifier();
    String json = serializeWithStats(modifier);
    assertThat(json.contains("Trait Modifier"), is(true));
  }

  @Test
  public void roundTripForTraitModifier() throws Exception {
    TraitModifyingStats modifier = GsonStatMother.createTraitModifier();
    String json = serializeWithStats(modifier);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(gson.toJson(readTemplate), is(json));
  }

  private IEquipmentTemplate deserialize(String json) {
    return gson.fromJson(json);
  }

  private String serializeWithStats(IEquipmentStats rangedWepaon) {
    originalTemplate.addStats(rangedWepaon);
    return gson.toJson(originalTemplate);
  }
}