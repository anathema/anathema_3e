package net.sf.anathema.hero.abilities.advance;

import com.google.common.collect.Iterables;
import net.sf.anathema.hero.traits.display.Traits;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.NullTraitState;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateType;

import java.util.ArrayList;
import java.util.List;

public class GroupedCalculationTraitHolder implements PointCalculationTraitHolder {

  private final List<PointCalculationTraitHolder> holders = new ArrayList<>();

  @Override
  public TraitState getState(Trait trait) {
    for (PointCalculationTraitHolder holder : holders) {
      if (holder.contains(trait)) {
        return holder.getState(trait);
      }
    }
    return new NullTraitState();
  }

  @Override
  public Traits getAll() {
    Traits traits = new Traits();
    for (PointCalculationTraitHolder holder : holders) {
      for (Trait trait : holder.getAll()) {
        traits.add(trait);
      }
    }
    return traits;
  }

  @Override
  public Iterable<TraitStateType> getAvailableTraitStates() {
    List<TraitStateType> types = new ArrayList<>();
    for (PointCalculationTraitHolder holder : holders) {
      Iterables.addAll(types, holder.getAvailableTraitStates());
    }
    return types;
  }

  @Override
  public boolean contains(Trait trait) {
    for (PointCalculationTraitHolder holder : holders) {
      if (holder.contains(trait)) {
        return true;
      }
    }
    return false;
  }

  public void add(PointCalculationTraitHolder traitHolder) {
    holders.add(traitHolder);
  }
}