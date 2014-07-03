package net.sf.anathema.hero.attributes.advance;

import net.sf.anathema.lib.util.Identifier;

public enum AttributeGroupPriority implements Identifier {

  Primary {
    @Override
    public void accept(AttributeGroupPriorityVisitor visitor) {
      visitor.acceptPrimary();
    }
  },
  Secondary {
    @Override
    public void accept(AttributeGroupPriorityVisitor visitor) {
      visitor.acceptSecondary();
    }
  },
  Tertiary {
    @Override
    public void accept(AttributeGroupPriorityVisitor visitor) {
      visitor.acceptTertiary();
    }
  };

  public abstract void accept(AttributeGroupPriorityVisitor visitor);

  @Override
  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}