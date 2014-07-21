package net.sf.anathema.magic.description.model;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParagraphsTest {

  @Test
  public void isInitiallyEmpty() throws Exception {
    Paragraphs paragraphs = new Paragraphs();
    assertThat(paragraphs.isEmpty(), is(true));
  }

  @Test
  public void isNotEmptyWhenCreatedWithContent() throws Exception {
    Paragraphs paragraphs = new Paragraphs("Content");
    assertThat(paragraphs.isEmpty(), is(false));
  }

  @Test
  public void iteratesOverContent() throws Exception {
    Paragraphs paragraphs = new Paragraphs("Content","More");
    Iterator<String> iterator = paragraphs.iterator();
    assertThat(iterator.next(), is("Content"));
    assertThat(iterator.next(), is("More"));
  }
}