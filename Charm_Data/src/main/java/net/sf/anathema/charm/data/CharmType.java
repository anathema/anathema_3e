package net.sf.anathema.charm.data;

import net.sf.anathema.lib.util.Identifier;

public enum CharmType implements Identifier {
  Simple() {
    @Override
    public void accept(CharmTypeVisitor visitor) {
      visitor.visitSimple(this);
    }
  },
  ExtraAction() {
    @Override
    public void accept(CharmTypeVisitor visitor) {
      visitor.visitExtraAction(this);
    }
  },
  Reflexive() {
    @Override
    public void accept(CharmTypeVisitor visitor) {
      visitor.visitReflexive(this);
    }
  },
  Supplemental() {
    @Override
    public void accept(CharmTypeVisitor visitor) {
      visitor.visitSupplemental(this);
    }
  },
  Permanent() {
    @Override
    public void accept(CharmTypeVisitor visitor) {
      visitor.visitPermanent(this);
    }
  },
  Special() {
    @Override
    public void accept(CharmTypeVisitor visitor) {
      visitor.visitSpecial(this);
    }
  };

  @Override
  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }

  public abstract void accept(CharmTypeVisitor visitor);
}