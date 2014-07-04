package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.library.collection.Closure;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;

public interface AgnosticTree {
  void setUiConfiguration(AgnosticUIConfiguration configuration);

  AgnosticTreeNode createRootNode(String rootText);

  void whenSelectionChangesDo(Closure<Object[]> closure);
}
