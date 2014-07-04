package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.library.message.MessageType;

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
        messaging.addTemporaryMessage(MessageType.Information, "AnathemaCore.Tools.RepositoryView.ItemAddedMessage",
                file.getPrintName());
      }

      @Override
      public void printNameFileRemoved(PrintNameFile file) {
        messaging.addTemporaryMessage(MessageType.Information, "AnathemaCore.Tools.RepositoryView.ItemRemovedMessage",
                file.getPrintName());
      }
    });
  }
}
