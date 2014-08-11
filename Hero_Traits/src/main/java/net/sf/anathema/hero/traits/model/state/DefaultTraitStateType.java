package net.sf.anathema.hero.traits.model.state;

public class DefaultTraitStateType implements TraitStateType {
  public static TraitStateType Default = new DefaultTraitStateType();
  
  @Override
  public boolean countsAs(TraitStateType otherState) {
    return otherState instanceof DefaultTraitStateType;
  }

  @Override
  public String getId() {
    return "Default";
  }
}
