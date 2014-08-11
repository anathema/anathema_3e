package net.sf.anathema.hero.traits.model.state;

public class SupernalTraitStateType implements TraitStateType {
  public static TraitStateType Supernal = new SupernalTraitStateType();

  @Override
  public boolean countsAs(TraitStateType otherState) {
    return otherState instanceof SupernalTraitStateType || otherState instanceof CasteTraitStateType;
  }

  @Override
  public String getId() {
    return "Supernal";
  }
}
