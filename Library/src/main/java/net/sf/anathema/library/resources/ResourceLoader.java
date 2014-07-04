package net.sf.anathema.library.resources;

import java.util.Set;

public interface ResourceLoader {

  Set<ResourceFile> getResourcesMatching(String namePattern);
}