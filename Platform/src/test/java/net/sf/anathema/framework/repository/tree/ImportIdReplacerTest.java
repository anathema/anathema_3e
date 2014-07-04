package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.lib.io.InputOutput;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ImportIdReplacerTest {

  @Test
  public void replacesRepositoryId() throws Exception {
    InputStream stream = new ByteArrayInputStream("{\"repositoryId\": \"old\"}".getBytes());
    InputStream newStream = new ImportIdReplacer("old", "new").createStreamWithLegalId(stream);
    String replacementString = InputOutput.toString(newStream);
    assertThat(replacementString, containsString("\"repositoryId\": \"new\""));
  }
}
