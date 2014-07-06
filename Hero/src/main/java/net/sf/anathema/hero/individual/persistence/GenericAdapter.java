package net.sf.anathema.hero.individual.persistence;

import com.google.gson.TypeAdapter;

public class GenericAdapter<T> {
  
  public final Class<T> type;
  public final TypeAdapter<T> adapter;

  public GenericAdapter(Class<T> type, TypeAdapter<T> adapter) {
    this.type = type;
    this.adapter = adapter;
  }
}
