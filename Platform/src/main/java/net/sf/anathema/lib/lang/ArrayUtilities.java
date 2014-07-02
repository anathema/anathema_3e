package net.sf.anathema.lib.lang;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ArrayUtilities {

  public static <T> T getFirst(T[] array, Predicate<T> predicate) {
    return Stream.of(array).filter(predicate).findFirst().orElse(null);
  }

  public static <R> int indexOf(R[] array, R value) {
    int index = ArrayUtils.indexOf(array, value);
    if (index != ArrayUtils.INDEX_NOT_FOUND) {
      return index;
    }
    throw new IllegalArgumentException("Value not contained in array: " + value);
  }

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

  @SuppressWarnings("unchecked")
  public static <I, O> O[] transform(
          I[] array,
          Class<? super O> clazz,
          Function<I, O> transformer) {
    O[] transformed = (O[]) Array.newInstance(clazz, array.length);
    return Stream.of(array).map(transformer).collect(toList()).toArray(transformed);
  }
}