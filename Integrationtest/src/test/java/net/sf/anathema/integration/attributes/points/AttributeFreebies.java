package net.sf.anathema.integration.attributes.points;

public class AttributeFreebies {

  public int primary = 0;
  public int secondary = 0;
  public int tertiary = 0;

  public AttributeFreebies withPrimary(int points) {
    this.primary = points;
    return this;
  }

  public AttributeFreebies withSecondary(int points) {
    this.secondary = points;
    return this;
  }

  public AttributeFreebies withTertiary(int points) {
    this.tertiary = points;
    return this;
  }
}
