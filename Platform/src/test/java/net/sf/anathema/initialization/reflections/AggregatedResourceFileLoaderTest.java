package net.sf.anathema.initialization.reflections;

import com.google.common.collect.Sets;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.library.resources.ResourceFileLoader;
import net.sf.anathema.platform.dependencies.AggregatedResourceFileLoader;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AggregatedResourceFileLoaderTest {

  @Test
  public void isEmptyWithoutDelegate() throws Exception {
    AggregatedResourceFileLoader loader = new AggregatedResourceFileLoader();
    Set<ResourceFile> result = loader.getResourcesMatching("X");
    assertThat(result.isEmpty(), is(true));
  }

  @Test
  public void providesResourcesFromDelegate() throws Exception {
    ResourceFileLoader delegate = mock(ResourceFileLoader.class);
    ResourceFile element = mock(ResourceFile.class);
    when(delegate.getResourcesMatching("X")).thenReturn(Sets.newHashSet(element));
    AggregatedResourceFileLoader loader = new AggregatedResourceFileLoader(delegate);
    Set<ResourceFile> result = loader.getResourcesMatching("X");
    assertThat(result, hasItem(element));
  }
}
