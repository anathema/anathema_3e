package net.sf.anathema.hero.traits.model.state;

public class CasteTraitStateType implements TraitStateType {
  public static TraitStateType Caste = new CasteTraitStateType();

  @Override
  public boolean countsAs(TraitStateType otherState) {
    return otherState instanceof CasteTraitStateType;
  }

  @Override
  public String getId() {
    return "Caste";
  }
}
