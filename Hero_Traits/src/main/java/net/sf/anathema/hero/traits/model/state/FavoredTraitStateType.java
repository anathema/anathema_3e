package net.sf.anathema.hero.traits.model.state;

public class FavoredTraitStateType implements TraitStateType {
  public static TraitStateType Favored = new FavoredTraitStateType();

  @Override
  public boolean countsAs(TraitStateType otherState) {
    return otherState instanceof FavoredTraitStateType;
  }

  @Override
  public String getId() {
    return "Favored";
  }
}
