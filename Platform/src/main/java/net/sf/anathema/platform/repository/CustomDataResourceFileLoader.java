package net.sf.anathema.platform.repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.library.resources.ResourceFileLoader;
import net.sf.anathema.platform.dependencies.ExternalResourceFile;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomDataResourceFileLoader implements ResourceFileLoader {
  public static final String CUSTOM_FOLDER_NAME = "custom";
  private final File customFolder;

  public CustomDataResourceFileLoader(RepositoryLocationResolver resolver) {
    File folder = new File(resolver.resolve(), CUSTOM_FOLDER_NAME);
    this.customFolder = new RepositoryFolderWorker().createFolder(folder);
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(final String namePattern) {
    File[] elements = customFolder.listFiles((dir, name) -> name.matches(namePattern));
    Collection<File> customFiles = Lists.newArrayList(elements);
    HashSet<ResourceFile> resourceFiles = Sets.newHashSet();
    for (File file : customFiles) {
      resourceFiles.add(new ExternalResourceFile(file));
    }
    return resourceFiles;
  }
}