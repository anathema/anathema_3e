package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.Vetor;
import net.sf.anathema.platform.environment.Environment;

public class RepositoryItemDeletionPresenter {

  private final IRepositoryTreeModel repositoryModel;
  private final IRepositoryTreeView treeView;
  private final AmountMessaging messaging;
  private Environment environment;

  public RepositoryItemDeletionPresenter(Environment environment, RepositoryTreeModel repositoryTreeModel,
                                         IRepositoryTreeView treeView, AmountMessaging fileCountMessaging) {
    this.environment = environment;
    this.repositoryModel = repositoryTreeModel;
    this.treeView = treeView;
    this.messaging = fileCountMessaging;
  }

  public void initPresentation() {
    Tool tool = treeView.addTool();
    tool.setTooltip(environment.getString("AnathemaCore.Tools.RepositoryView.DeleteToolTip"));
    tool.setText(environment.getString("AnathemaCore.Tools.RepositoryView.DeleteName"));
    tool.setIcon(new FileUi().getRemoveFilePath());
    tool.setCommand(() -> {
      String message = environment.getString("AnathemaCore.Tools.RepositoryView.DeleteMessage");
      String title = environment.getString("AnathemaCore.DialogTitle.ConfirmationDialog");
      Vetor vetor = treeView.createVetor(message, title);
      vetor.requestPermissionFor(() -> {
        try {
          int itemCount = repositoryModel.getPrintNameFilesInSelection().length;
          repositoryModel.deleteSelection();
          messaging.addMessage("AnathemaCore.Tools.RepositoryView.DeleteDoneMessage", itemCount);
        } catch (PersistenceException e) {
          environment.handle(e, environment.getString("AnathemaCore.Tools.RepositoryView.RepositoryError"));
        }
      });
    });
    repositoryModel.addTreeSelectionChangeListener(() -> {
      if (repositoryModel.canSelectionBeDeleted()) {
        tool.enable();
      } else {
        tool.disable();
      }
    });
    tool.disable();
  }
}
