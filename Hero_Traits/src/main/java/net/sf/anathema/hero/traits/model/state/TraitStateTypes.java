package net.sf.anathema.hero.traits.model.state;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static net.sf.anathema.hero.traits.model.state.CasteTraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.DefaultTraitStateType.Default;
import static net.sf.anathema.hero.traits.model.state.FavoredTraitStateType.Favored;
import static net.sf.anathema.hero.traits.model.state.SupernalTraitStateType.Supernal;

public class TraitStateTypes implements Iterable<TraitStateType> {
  private final static List<TraitStateType> allTypes = Lists.newArrayList(Default, Favored, Caste, Supernal);

  public static TraitStateTypes withAllKnown() {
    return new TraitStateTypes(allTypes);
  }

  public static TraitStateTypes limitedTo(Collection<String> ids) {
    List<TraitStateType> availableTypes = ids.stream().map(id -> {
      for (TraitStateType type : allTypes) {
        if (type.getId().equals(id)) {
          return type;
        }
      }
      throw new RuntimeException("Unknown type: " + id);
    }).collect(Collectors.toList());
    return new TraitStateTypes(availableTypes);
  }

  private final List<TraitStateType> types;

  private TraitStateTypes(List<TraitStateType> availableTypes) {
    this.types = availableTypes;
  }

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
