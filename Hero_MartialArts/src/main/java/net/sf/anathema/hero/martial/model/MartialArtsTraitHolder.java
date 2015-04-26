package net.sf.anathema.hero.martial.model;

import net.sf.anathema.hero.abilities.advance.PointCalculationTraitHolder;
import net.sf.anathema.hero.traits.display.Traits;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.DefaultTraitStateType;
import net.sf.anathema.hero.traits.model.state.NullTraitState;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateType;

import java.util.Collections;

public class MartialArtsTraitHolder implements PointCalculationTraitHolder {
  private MartialArtsModel martialArtsModel;

  public MartialArtsTraitHolder(MartialArtsModel martialArtsModel) {
    this.martialArtsModel = martialArtsModel;
  }

  @Override
  public TraitState getState(Trait trait) {
    return new NullTraitState();
  }

  @Override
  public Traits getAll() {
    Traits traits = new Traits();
    for (Trait trait : martialArtsModel.getLearnedStyles()) {
      traits.add(trait);
    }
    for (Trait trait : martialArtsModel.getAvailableStyles()) {
      traits.add(trait);
    }
    return traits;
  }

  @Override
  public Iterable<TraitStateType> getAvailableTraitStates() {
    return Collections.singletonList(DefaultTraitStateType.Default);
  }

  @Override
  public boolean contains(Trait trait) {
    for (Trait candidate : martialArtsModel.getLearnedStyles()) {
      if(candidate.equals(trait)){
        return true;
      }
    }
    for (Trait candidate : martialArtsModel.getAvailableStyles()) {
      if(candidate.equals(trait)){
        return true;
      }
    }
    return false;
  }
}
