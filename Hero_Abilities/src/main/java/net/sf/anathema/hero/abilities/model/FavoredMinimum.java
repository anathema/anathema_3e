package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.rules.minimum.DynamicMinimum;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.library.event.ChangeListener;

import static net.sf.anathema.hero.traits.model.state.TraitStateType.Favored;

public class FavoredMinimum implements DynamicMinimum {
  private TraitStateMap stateMap;
  private final Trait trait;

  public FavoredMinimum(TraitStateMap stateMap, Trait trait) {
    this.stateMap = stateMap;
    this.trait = trait;
  }

  @Override
  public int getMinimum() {
    boolean isFavored = stateMap.getState(trait).hasState(Favored);
    return isFavored ? 1 : 0;
  }

  @Override
  public void addChangedListener(ChangeListener listener) {
    stateMap.getState(trait).addTraitStateChangedListener((TraitStateType state) -> {
      listener.changeOccurred();
    });
  }
}
