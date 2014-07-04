package net.sf.anathema.platform.dependencies;

import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.library.resources.ResourceFileLoader;

import java.util.HashSet;
import java.util.Set;

public class AggregatedResourceFileLoader implements ResourceFileLoader {

  private final ResourceFileLoader[] delegates;

  public AggregatedResourceFileLoader(ResourceFileLoader... delegates) {
    this.delegates = delegates;
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(String namePattern) {
    Set<ResourceFile> files = new HashSet<>();
    for (ResourceFileLoader loader : delegates) {
      files.addAll(loader.getResourcesMatching(namePattern));
    }
    return files;
  }
}