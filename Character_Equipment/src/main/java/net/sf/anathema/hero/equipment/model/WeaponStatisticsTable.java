package net.sf.anathema.hero.equipment.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.sf.anathema.character.equipment.creation.model.WeaponTag;

import static net.sf.anathema.character.equipment.creation.model.WeaponTag.*;

public class WeaponStatisticsTable {

  private static final String Accuracy = "Accuracy";
  public static final String Damage = "Damage";
  private static final String Overwhelming = "Overwhelming";
  private static final String Defense = "Defense";

  public static WeaponStatisticsTable forMundaneWeapons() {
    WeaponStatisticsTable mundane = new WeaponStatisticsTable();
    mundane.put(Light, 4, 7, 1, 0);
    mundane.put(Medium, 2, 9, 1, 1);
    mundane.put(Heavy, 0, 11, 1, -1);
    return mundane;
  }

  public static WeaponStatisticsTable forArtifactWeapons() {
    WeaponStatisticsTable artifacts = new WeaponStatisticsTable();
    artifacts.put(Light, 5, 10, 2, 1);
    artifacts.put(Medium, 3, 12, 3, 1);
    artifacts.put(Heavy, 1, 14, 4, 0);
    return artifacts;
  }

  private final Table<WeaponTag, String, Integer> table = HashBasedTable.create();

  private void put(WeaponTag size, int accuracy, int damage, int overwhelming, int defense) {
    table.put(size, Accuracy, accuracy);
    table.put(size, Damage, damage);
    table.put(size, Overwhelming, overwhelming);
    table.put(size, Defense, defense);
  }

  public WeaponStatisticsEntry forSize(WeaponTag size) {
    return new WeaponStatisticsEntry(size);
  }

  public class WeaponStatisticsEntry {
    private WeaponTag size;

    public WeaponStatisticsEntry(WeaponTag size) {
      this.size = size;
    }

    public int getAccuracy() {
      return table.get(size, Accuracy);
    }

    public int getDamage() {
      return table.get(size, Damage);
    }

    public int getOverwhelming() {
      return table.get(size, Overwhelming);
    }

    public int getDefense() {
      return table.get(size, Defense);
    }
  }
}
