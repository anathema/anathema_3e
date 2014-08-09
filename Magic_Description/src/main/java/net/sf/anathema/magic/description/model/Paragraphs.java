package net.sf.anathema.magic.description.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Paragraphs implements Iterable<String>{

  private final List<String> paragraphs = new ArrayList<>();

  public Paragraphs(String... paragraphs) {
    Collections.addAll(this.paragraphs, paragraphs);
  }
  
  public boolean isEmpty() {
    return paragraphs.isEmpty();
  }

  @Override
  public Iterator<String> iterator() {
    return paragraphs.iterator();
  }
}
