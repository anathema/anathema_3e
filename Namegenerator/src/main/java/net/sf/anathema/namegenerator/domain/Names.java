package net.sf.anathema.namegenerator.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Names implements Iterable<String> {

  private List<String> names = new ArrayList<>();

  public void add(String name) {
    names.add(name);
  }

  public String getFirst() {
    return names.get(0);
  }

  @Override
  public Iterator<String> iterator() {
    return names.iterator();
  }
}
