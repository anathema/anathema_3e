package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.library.interaction.model.Tool;

public interface IRepositoryTreeView extends VetorFactory {

  Tool addTool();

  AgnosticTree addAgnosticTree();
}