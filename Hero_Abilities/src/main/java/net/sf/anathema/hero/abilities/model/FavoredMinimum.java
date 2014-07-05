package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.rules.minimum.DynamicMinimum;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;
import net.sf.anathema.library.event.ChangeListener;

public class FavoredMinimum implements DynamicMinimum {
  private TraitStateMap stateMap;
  private final Trait trait;

  public FavoredMinimum(TraitStateMap stateMap, Trait trait) {
    this.stateMap = stateMap;
    this.trait = trait;
  }

  @Override
  public int getMinimum() {
    boolean isFavored = stateMap.getType(trait).equals(TraitState.Favored);
    return isFavored ? 1 : 0;
  }

  @Override
  public void addChangedListener(ChangeListener listener) {
    stateMap.addTraitStateChangedListener(trait, state -> {
      listener.changeOccurred();
    });
  }
}
