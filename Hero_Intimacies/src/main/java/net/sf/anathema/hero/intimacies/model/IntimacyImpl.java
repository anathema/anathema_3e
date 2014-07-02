package net.sf.anathema.hero.intimacies.model;

public class IntimacyImpl implements Intimacy {

  private final String name;
  private final Strength strength;
  private final Outlook outlook;
  private final Bond bond;

  public IntimacyImpl(String name, Strength strength, Outlook outlook, Bond bond) {
    this.name = name;
    this.strength = strength;
    this.outlook = outlook;
    this.bond = bond;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Strength getStrength() {
    return strength;
  }

  @Override
  public Outlook getOutlook() {
    return outlook;
  }

  @Override
  public Bond getBond() {
    return bond;
  }
}