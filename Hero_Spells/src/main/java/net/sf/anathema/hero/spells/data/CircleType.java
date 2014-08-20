package net.sf.anathema.hero.spells.data;

import net.sf.anathema.library.identifier.Identifier;

public enum CircleType implements Identifier {

  Terrestrial {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitTerrestrial(this);
    }
  },
  Celestial {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitCelestial(this);
    }
  },
  Solar {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitSolar(this);
    }
  };

  @Override
  public String getId() {
    return name();
  }

  public abstract void accept(ICircleTypeVisitor visitor);
}