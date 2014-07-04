package net.sf.anathema.library.initialization;

import java.util.HashMap;
import java.util.Map;

public class RegistryImpl<I, V> implements Registry<I, V> {

  private final Map<I, V> objects = new HashMap<>();
  private V defaultValue;

  public RegistryImpl() {
    this(null);
  }

  public RegistryImpl(V defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public void register(I id, V anObject) {
    objects.put(id, anObject);
  }

  @Override
  public V get(I id) {
    if (defaultValue != null && !objects.containsKey(id)) {
      return defaultValue;
    }
    return objects.get(id);
  }
}