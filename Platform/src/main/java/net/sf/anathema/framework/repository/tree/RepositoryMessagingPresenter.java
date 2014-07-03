package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.message.MessageType;

public class RepositoryMessagingPresenter {

  private final RepositoryTreeModel repositoryTreeModel;
  private final Messaging messaging;

  public RepositoryMessagingPresenter(RepositoryTreeModel repositoryTreeModel, Messaging messaging) {
    this.repositoryTreeModel = repositoryTreeModel;
    this.messaging = messaging;
  }

  public void initPresentation() {
    repositoryTreeModel.addRepositoryTreeModelListener(new IRepositoryTreeModelListener() {
      @Override
      public void printNameFileAdded(PrintNameFile file) {
        messaging.addMessage("AnathemaCore.Tools.RepositoryView.ItemAddedMessage",
                MessageType.INFORMATION,
                file.getPrintName());
      }

      @Override
      public void printNameFileRemoved(PrintNameFile file) {
        messaging.addMessage("AnathemaCore.Tools.RepositoryView.ItemRemovedMessage",
                MessageType.INFORMATION,
                file.getPrintName());
      }
    });
  }
}
