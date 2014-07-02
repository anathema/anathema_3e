package net.sf.anathema.hero.intimacies.model;

public class IntimacyImpl implements Intimacy {

  private final String name;

  public IntimacyImpl(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}