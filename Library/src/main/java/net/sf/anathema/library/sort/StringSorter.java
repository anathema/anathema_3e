package net.sf.anathema.library.sort;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StringSorter<T> {

  private Function<T, String> toString;

  public StringSorter(Function<T, String> toString) {
    this.toString = toString;
  }

  public List<T> sortAscending(List<T> originalGroup) {
    Stream<T> ids = originalGroup.stream();
    return ids.sorted((id1, id2) -> {
      String firstGroupName = toString.apply(id1);
      String secondGroupName = toString.apply(id2);
      return firstGroupName.compareToIgnoreCase(secondGroupName);
    }).collect(toList());
  }
}
