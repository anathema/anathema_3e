package net.sf.anathema.hero.traits.model.state;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

import static net.sf.anathema.hero.traits.model.state.CasteTraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.DefaultTraitStateType.Default;
import static net.sf.anathema.hero.traits.model.state.FavoredTraitStateType.Favored;
import static net.sf.anathema.hero.traits.model.state.SupernalTraitStateType.Supernal;

public class TraitStateTypes implements Iterable<TraitStateType>{

  public final static List<TraitStateType> types = Lists.newArrayList(Default, Favored, Caste, Supernal);

  public TraitStateType getNext(TraitStateType currentState, int advance) {
    int ordinal = types.indexOf(currentState);
    return types.get((ordinal + advance) % types.size());
  }

  @Override
  public Iterator<TraitStateType> iterator() {
    return types.iterator();
  }

  public int size() {
    return types.size();
  }

  public TraitStateType lookUp(String stateId) {
    return types.stream().filter(state -> state.getId().equals(stateId)).findFirst().get();
  }
}
