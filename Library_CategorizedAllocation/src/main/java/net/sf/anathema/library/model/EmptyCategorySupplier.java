package net.sf.anathema.library.model;

import java.util.ArrayList;
import java.util.List;

public class EmptyCategorySupplier implements OptionalEntryCategorySupplier {

  @Override
  public List<OptionalEntryCategory> getAllCategories() {
    return new ArrayList<>();
  }

  @Override
  public List<OptionalEntryCategory> getCurrentCategories() {
     return new ArrayList<>();
  }

}
