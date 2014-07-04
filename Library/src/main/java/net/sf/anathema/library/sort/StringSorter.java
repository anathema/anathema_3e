package net.sf.anathema.library.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class StringSorter<T> {

  private Function<T, String> toString;

  public StringSorter(Function<T, String> toString) {
    this.toString = toString;
  }

  public List<T> sortAscending(List<T> originalGroup) {
    ArrayList<T> listCopy = new ArrayList<>(originalGroup);
    Collections.sort(listCopy, (id1, id2) -> {
      String firstGroupName = toString.apply(id1);
      String secondGroupName = toString.apply(id2);
      return firstGroupName.compareToIgnoreCase(secondGroupName);
    });
    return listCopy;
  }
}
