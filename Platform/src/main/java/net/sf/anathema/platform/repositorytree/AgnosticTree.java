package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.library.collection.Closure;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;

import java.util.List;

public interface AgnosticTree {
  void setUiConfiguration(AgnosticUIConfiguration<Object> configuration);

  AgnosticTreeNode createRootNode(String rootText);

  void whenSelectionChangesDo(Closure<List<Object>> closure);
}
