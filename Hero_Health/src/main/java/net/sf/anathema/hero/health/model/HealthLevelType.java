package net.sf.anathema.hero.health.model;

import net.sf.anathema.library.identifier.Identifier;

public enum HealthLevelType implements Identifier {

  ZERO("0", 0),
  ONE("1", -1),
  TWO("2", -2),
  FOUR("4", -4),
  INCAPACITATED("Incapacitated", Integer.MIN_VALUE),
  DYING("Dying", Integer.MIN_VALUE);

  private final String id;
  private final int value;

  @Override
  public String getId() {
    return id;
  }

  private HealthLevelType(String id, int value) {
    this.id = id;
    this.value = value;
  }

  @Override
  public String toString() {
    return getId();
  }

  public int getIntValue() {
    return value;
  }

  public static HealthLevelType byGameNotation(String gameNotation) {
    try {
      Integer integer = Integer.valueOf(gameNotation);
      for (HealthLevelType healthLevelType : values()) {
        if (healthLevelType.getIntValue() == integer) {
          return healthLevelType;
        }
      }
      throw new IllegalArgumentException("Unknown Health Level Type:" + gameNotation);
    } catch (NumberFormatException e) {
      if (gameNotation.equals(INCAPACITATED.getId())) {
        return INCAPACITATED;
      }
      throw new IllegalArgumentException("Unknown Health Level Type:" + gameNotation);
    }
  }
}