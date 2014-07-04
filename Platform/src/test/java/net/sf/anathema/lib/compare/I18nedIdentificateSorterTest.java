package net.sf.anathema.lib.compare;

import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.sort.I18nedIdentificateSorter;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class I18nedIdentificateSorterTest {

  @Test
  public void worksOnEmptyLists() throws Exception {
    I18nedIdentificateSorter sorter = new I18nedIdentificateSorter(null);
    List<Identifier> originalGroup = Collections.emptyList();
    List<Identifier> identificates = sorter.sortAscending(originalGroup);
    assertThat(identificates.size(), is(0));
  }
}
