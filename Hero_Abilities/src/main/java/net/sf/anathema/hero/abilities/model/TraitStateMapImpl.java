package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitImpl;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.rules.minimum.DynamicMinimum;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateChangedListener;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TraitStateMapImpl implements TraitStateMap, TraitStateCollection {

  private final Hero hero;
  private Map<TraitType, TraitState> traitsByType = new HashMap<>();

  public TraitStateMapImpl(Hero hero) {
    this.hero = hero;
  }

  public void addTrait(TraitImpl trait) {
    traitsByType.put(trait.getType(), trait.getStateModel());
    DynamicMinimum favoredMinimum = new FavoredMinimum(this, trait);
    TraitModelFetcher.fetch(hero).getMinimumMap().addMinimum(trait.getType(), favoredMinimum);
  }

  public void addTraitStateChangedListener(Trait trait, TraitStateChangedListener listener) {
    getState(trait).addTraitStateChangedListener(listener);
  }

  public void forEach(Consumer<TraitState> consumer) {
    traitsByType.values().forEach(consumer);
  }

  public TraitState getState(Trait trait) {
    return getState(trait.getType());
  }

  @Override
  public TraitState getState(TraitType traitType) {
    return traitsByType.get(traitType);
  }
}
