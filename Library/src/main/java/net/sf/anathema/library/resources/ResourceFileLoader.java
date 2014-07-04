package net.sf.anathema.library.resources;

import java.util.Set;

public interface ResourceFileLoader {

  Set<ResourceFile> getResourcesMatching(String namePattern);
}