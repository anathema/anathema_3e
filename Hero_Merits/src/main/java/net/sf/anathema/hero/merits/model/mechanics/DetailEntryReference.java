package net.sf.anathema.hero.merits.model.mechanics;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DetailEntryReference {
  @SuppressWarnings("FieldCanBeLocal")
  private final String reference;

  public DetailEntryReference(String reference) {
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