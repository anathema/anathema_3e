package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.thaumaturgy.compiler.json.template.RitualTemplate;
import net.sf.anathema.library.model.trait.AbstractOptionalTraitOption;

public class RitualImpl extends AbstractOptionalTraitOption implements ThaumaturgyRitual {

  private final String name;
  private final boolean[] legalValues = new boolean[RITUAL_MAX_LEVEL];

  public RitualImpl(RitualTemplate template) {
    this.name = template.name;
    parseLegalValues(template);
  }

  private void parseLegalValues(RitualTemplate template) {
    legalValues[levelToIndex(RitualLevel.Basic)] = template.basicRitual;
    legalValues[levelToIndex(RitualLevel.Advanced)] = template.advancedRitual;
  }

  @Override
  public String getId() {
    return name;
  }

  @Override
  public int getMinimumValue() {
    for (int i = RitualLevel.getMinimumLevel().getValue(); i <= RitualLevel.getMaximumLevel().getValue(); i++) {
      if (isLegalValue(i)) {
        return i;
      }
    }
    return RitualLevel.getMinimumLevel().getValue();
  }

  @Override
  public int getMaximumValue() {
    for (int i = RitualLevel.getMaximumLevel().getValue(); i >= RitualLevel.getMinimumLevel().getValue(); i--) {
      if (isLegalValue(i)) {
        return i;
      }
    }
    return RitualLevel.getMaximumLevel().getValue();
  }

  @Override
  public boolean isLegalValue(int value) {
    if (value < RitualLevel.getMinimumLevel().getValue() || value > RitualLevel.getMaximumLevel().getValue()) {
      return false;
    }
    return legalValues[value - 1];
  }
  
  private int levelToIndex(RitualLevel level) {
  	return level.ordinal();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ThaumaturgyRitual) {
      String id = ((ThaumaturgyRitual) obj).getId();
      return id.equals(getId());
    }
    return false;
  }

  @Override
  public String toString() {
    return getId();
  }
}