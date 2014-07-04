package net.sf.anathema.platform.repository.printname;

import net.sf.anathema.library.io.InputOutput;
import net.sf.anathema.library.logging.Logger;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.repository.IRepositoryFileResolver;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileReferenceAccess<R> implements ReferenceAccess<R> {

  private static final Logger logger = Logger.getLogger(FileReferenceAccess.class);
  private final IRepositoryFileResolver resolver;
  private IItemType type;
  private ReferenceBuilder<R> builder;

  public FileReferenceAccess(IRepositoryFileResolver resolver, IItemType type, ReferenceBuilder<R> builder) {
    this.resolver = resolver;
    this.type = type;
    this.builder = builder;
  }

  @Override
  public Collection<R> collectAllItemReferences() {
    List<R> referenceList = new ArrayList<>();
    for (File subFile : collectItemFiles(type)) {
      if (type.getRepositoryConfiguration().isItemSavedToSingleFile()) {
        addReference(subFile, builder, referenceList);
      }
      else {
        File mainFile = resolver.getMainFile(subFile, type.getRepositoryConfiguration());
        addReference(mainFile, builder, referenceList);
      }
    }
    return referenceList;
  }

  private File[] collectItemFiles(IItemType type) {
    File repositoryFolder = resolver.getFolder(type.getRepositoryConfiguration());
    File[] files = repositoryFolder.listFiles();
    if (files == null) {
      return new File[0];
    }
    return files;
  }

  private <R> void addReference(File itemFile, ReferenceBuilder<R> builder, List<R> itemReferences) {
    try {
      String itemContent = InputOutput.toString(itemFile);
      R reference = builder.create(itemContent);
      if (reference != null) {
        itemReferences.add(reference);
      }
    } catch (Exception e) {
      logger.error(e);
    }
  }
}
