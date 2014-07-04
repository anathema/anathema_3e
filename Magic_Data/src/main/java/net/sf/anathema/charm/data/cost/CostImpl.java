package net.sf.anathema.charm.data.cost;

import net.sf.anathema.library.lang.ReflectionEqualsObject;

public class CostImpl extends ReflectionEqualsObject implements Cost {

  public static final Cost NULL_COST = new CostImpl("0", "", false);
  private final String text;
  private final String costString;
  private final boolean permanent;

  public CostImpl(String costString, String text, boolean permanent) {
    this.costString = costString;
    this.text = text;
    this.permanent = permanent;
  }

  @Override
  public String getCost() {
    return costString;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public boolean isPermanent() {
    return permanent;
  }

  @Override
  public String toString() {
    String textRepresentation = text == null ? "" : " " + text;
    String durationRepresentation = permanent ? " permanent" : " temporary";
    return costString + textRepresentation + durationRepresentation;
  }
}