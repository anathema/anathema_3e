package net.sf.anathema.library.model;

import java.util.List;

public interface OptionalEntryCategorySupplier {
  List<OptionalEntryCategory> getAllCategories();
  
  List<OptionalEntryCategory> getCurrentCategories();
}
