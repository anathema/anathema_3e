package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.library.interaction.model.Tool;

public interface IRepositoryTreeView extends VetorFactory {

  Tool addTool();

  AgnosticTree addAgnosticTree();
}