package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.library.io.FileChooserConfiguration;
import net.sf.anathema.library.io.FileExtension;
import net.sf.anathema.library.io.SingleFileChooser;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControlledFileChooserTest {

  private final FileChooserConfiguration configuration = new FileChooserConfiguration(new FileExtension("description", "filter"), "name");

  @Test
  public void findsFileViaRegisteredFileChooser() throws Exception {
    Path expectation = Paths.get(".");
    SingleFileChooser singleFileChooser = mock(SingleFileChooser.class);
    when(singleFileChooser.selectSaveFile(configuration)).thenReturn(expectation);
    assertThat(new ControlledFileChooser(singleFileChooser, configuration).getPrintFile(), is(expectation));
  }
}