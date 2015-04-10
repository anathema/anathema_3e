package net.sf.anathema.hero.traits.model.group;

import net.sf.anathema.library.identifier.Identifier;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class GroupName implements Identifier {
  private String groupId;

  public GroupName(String groupId) {
    this.groupId = groupId;
  }

  @Override
  public String getId() {
    return groupId;
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(o, this);
  }

  @Override
  public int hashCode() {
    return groupId.hashCode();
  }
}
