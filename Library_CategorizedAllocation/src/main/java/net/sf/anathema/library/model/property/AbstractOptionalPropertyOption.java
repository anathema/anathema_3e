package net.sf.anathema.library.model.property;

import net.sf.anathema.library.model.AbstractOptionalEntryOption;

public abstract class AbstractOptionalPropertyOption extends AbstractOptionalEntryOption implements OptionalPropertyOption {
  
  protected final String name;
  
  protected AbstractOptionalPropertyOption(String name) {
    this.name = name;
  }
  
  @Override
  public String getId() {
    return name;
  }
  
  @Override
  public String toString() {
    return getId();
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof AbstractOptionalPropertyOption) {
      return name.equals(((AbstractOptionalPropertyOption) obj).name);
    }
    return false;
  }

}
