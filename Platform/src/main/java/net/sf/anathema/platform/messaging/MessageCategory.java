package net.sf.anathema.platform.messaging;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MessageCategory {

  private String category;

  public MessageCategory(String category) {
    this.category = category;
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return "MessageCategory:" + category;
  }
}
