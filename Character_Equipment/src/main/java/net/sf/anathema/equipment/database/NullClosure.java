package net.sf.anathema.equipment.database;

import net.sf.anathema.library.collection.Closure;

public class NullClosure<S> implements Closure<S> {
  @Override
  public void execute(S value) {
    //nothing to do;
  }
}
