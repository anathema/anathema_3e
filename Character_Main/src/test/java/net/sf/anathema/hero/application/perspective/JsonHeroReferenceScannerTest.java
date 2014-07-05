package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.CharacterReference;
import net.sf.anathema.hero.dummy.DummyMundaneHeroType;
import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.library.io.InputOutput;
import net.sf.anathema.platform.item.RepositoryConfiguration;
import net.sf.anathema.platform.repository.IRepositoryFileResolver;
import net.sf.anathema.platform.repository.printname.SimpleRepositoryId;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonHeroReferenceScannerTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test
  public void closesFileAfterScanning() throws Exception {
    File testFile = folder.newFile();
    InputOutput.writeStringToFile(testFile, "{repositoryId: x, printName: y}");
    HeroTypes types = mock(HeroTypes.class);
    when(types.findById(anyString())).thenReturn(new DummyMundaneHeroType());
    IRepositoryFileResolver resolver = prepareResolverToReturnFile(testFile);
    CharacterReference reference = new CharacterReference(new SimpleRepositoryId("x"), "y");
    new JsonHeroReferenceScanner(types, resolver).getCasteType(reference);
    testFile.delete();
    assertThat(testFile.exists(), is(false));
  }

  private IRepositoryFileResolver prepareResolverToReturnFile(File testFile) {
    IRepositoryFileResolver resolver = mock(IRepositoryFileResolver.class);
    when(resolver.getMainFile(Mockito.any(RepositoryConfiguration.class), Mockito.any(String.class))).thenReturn(testFile);
    return resolver;
  }
}
