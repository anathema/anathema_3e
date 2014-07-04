package net.sf.anathema.library.initialization;

public interface Registry<I, V> {

  void register(I id, V anObject);

  V get(I id);
}