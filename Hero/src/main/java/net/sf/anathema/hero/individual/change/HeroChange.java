package net.sf.anathema.hero.individual.change;

public class HeroChange {

  public final ChangeFlavor flavor;
  public final ChangeMemento memento;

  public HeroChange(ChangeFlavor flavor, ChangeMemento memento) {
    this.flavor = flavor;
    this.memento = memento;
  }
}
