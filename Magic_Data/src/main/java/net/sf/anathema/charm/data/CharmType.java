package net.sf.anathema.charm.data;

import net.sf.anathema.library.identifier.Identifier;

public enum CharmType implements Identifier {
  Simple() {
    @Override
    public void accept(CharmTypeVisitor visitor) {
      visitor.visitSimple(this);
    }
  },
  Supplemental() {
    @Override
    public void accept(CharmTypeVisitor visitor) {
      visitor.visitSupplemental(this);
    }
  },
  Reflexive() {
    @Override
    public void accept(CharmTypeVisitor visitor) {
      visitor.visitReflexive(this);
    }
  },
  Permanent() {
    @Override
    public void accept(CharmTypeVisitor visitor) {
      visitor.visitPermanent(this);
    }
  };

  @Override
  public String getId() {
    return name();
  }

  public abstract void accept(CharmTypeVisitor visitor);
}