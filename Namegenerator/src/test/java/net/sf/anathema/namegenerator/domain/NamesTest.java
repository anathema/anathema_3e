package net.sf.anathema.namegenerator.domain;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NamesTest {

  @Test
  public void returnsFirstAddedName() throws Exception {
    Names names = new Names();
    names.add("First");
    names.add("Second");
    assertThat(names.getFirst(), is("First"));
  }

  @Test
  public void iteratesOverNames() throws Exception {
    Names names = new Names();
    names.add("First");
    names.add("Second");
    Iterator<String> iterator = names.iterator();
    assertThat(iterator.next(), is("First"));
    assertThat(iterator.next(), is("Second"));
  }
}