package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.library.text.SimpleTextualDescription;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;

public class SimpleTextualDescriptionTest {

  @Test
  public void neverHasNullText() throws Exception {
    assertThat(new SimpleTextualDescription().getText(), isEmptyString());
  }
}
