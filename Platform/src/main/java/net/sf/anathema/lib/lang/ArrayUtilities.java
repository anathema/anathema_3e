package net.sf.anathema.lib.lang;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ArrayUtilities {

  @SuppressWarnings("unchecked")
  public static <T> T[] concat(Class<T> clazz, T[] array1, T... array2) {
    if (array2 == null) {
      return array1;
    }
    if (array1 == null) {
      return array2;
    }
    ArrayList<T> list = new ArrayList<>();
    Collections.addAll(list, array1);
    Collections.addAll(list, array2);
    T[] concatenated = (T[]) Array.newInstance(clazz, list.size());
    return list.toArray(concatenated);
  }
}