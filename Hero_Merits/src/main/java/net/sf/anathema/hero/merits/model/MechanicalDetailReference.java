package net.sf.anathema.hero.merits.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MechanicalDetailReference {
  @SuppressWarnings("UnusedDeclaration")
  private final String reference;

  public MechanicalDetailReference(String reference) {
    this.reference = reference;
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object other) {
    return EqualsBuilder.reflectionEquals(this, other);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}