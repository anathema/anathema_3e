package net.sf.anathema.character.equipment.character.model.stats;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.sf.anathema.character.equipment.creation.model.ArmourTag;

import static net.sf.anathema.character.equipment.creation.model.ArmourTag.*;

public class ArmorStatisticsTable {

  private static final String Soak = "Soak";
  public static final String Hardness = "Hardness";
  private static final String Mobility = "Mobility";

  public static ArmorStatisticsTable forMundaneArmor() {
    ArmorStatisticsTable mundane = new ArmorStatisticsTable();
    mundane.put(Light, 2, 0, 0);
    mundane.put(Medium, 5, 0, -1);
    mundane.put(Heavy, 8, 0, -2);
    return mundane;
  }

  public static ArmorStatisticsTable forArtifactArmor() {
    ArmorStatisticsTable artifacts = new ArmorStatisticsTable();
    artifacts.put(Light, 4, 4, 0);
    artifacts.put(Medium, 8, 7, -1);
    artifacts.put(Heavy, 12, 10, -2);
    return artifacts;
  }

  private final Table<ArmourTag, String, Integer> table = HashBasedTable.create();

  private void put(ArmourTag size, int soak, int hardness, int mobility) {
    table.put(size, Soak, soak);
    table.put(size, Hardness, hardness);
    table.put(size, Mobility, mobility);
  }

  public ArmorStatisticsEntry forSize(ArmourTag size) {
    return new ArmorStatisticsEntry(size);
  }

  public class ArmorStatisticsEntry {
    private ArmourTag size;

    public ArmorStatisticsEntry(ArmourTag size) {
      this.size = size;
    }

    public int getSoak() {
      return table.get(size, Soak);
    }

    public int getHardness() {
      return table.get(size, Hardness);
    }

    public int getMobilityPenalty() {
      return table.get(size, Mobility);
    }
  }
}
