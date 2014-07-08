package net.sf.anathema.library.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class WeightedObjectSorter<V> {

  public abstract WeightedObject<V> createWeightedObject(V value, int weight);

  private List<WeightedObject<V>> getAscendingArray(V[] values, int[] weights) {
    List<WeightedObject<V>> weightedList = new ArrayList<>();
    for (int index = 0; index < values.length; index++) {
      V nextValue = values[index];
      weightedList.add(createWeightedObject(nextValue, weights[index]));
    }
    Collections.sort(weightedList);
    return weightedList;
  }

  public List<V> sortDescending(V[] values, int[] weights) {
    List<WeightedObject<V>> weightedList = getAscendingArray(values, weights);
    List<V> sortedList = new ArrayList<>();
    for (int index = weightedList.size() - 1; index > -1; index--) {
      sortedList.add(weightedList.get(index).getValue());
    }
    return sortedList;
  }
}