package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.message.MessageType;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.messaging.Messaging;
import net.sf.anathema.platform.repository.PrintNameFile;
import net.sf.anathema.platform.repository.access.RepositoryFileAccess;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class RepositoryItemDuplicationPresenter {

  private final RepositoryTreeModel model;
  private final IRepositoryTreeView view;
  private final Messaging messaging;
  private final Environment environment;

  public RepositoryItemDuplicationPresenter(Environment environment, RepositoryTreeModel repositoryTreeModel,
                                            IRepositoryTreeView treeView, Messaging messaging) {
    this.environment = environment;
    this.model = repositoryTreeModel;
    this.view = treeView;
    this.messaging = messaging;
  }

  public void initPresentation() {
    Tool tool = view.addTool();
    tool.setTooltip(environment.getString("AnathemaCore.Tools.RepositoryView.DuplicateToolTip"));
    tool.setText(environment.getString("AnathemaCore.Tools.RepositoryView.DuplicateName"));
    tool.setIcon(new FileUi().getDuplicateFilePath());
    tool.setCommand(() -> {
      try {
        PrintNameFile[] printNameFiles = model.getPrintNameFilesInSelection();
        for (PrintNameFile printNameFile : printNameFiles) {
          String id = printNameFile.getRepositoryId();
          IItemType type = printNameFile.getItemType();
          RepositoryFileAccess readAccess = model.getFileAccess(printNameFile);
          String mainFilePath = model.getMainFilePath(type, id);
          RepositoryImportHandler handler = new RepositoryImportHandler(model, type, id);
          for (File file : readAccess.getFiles()) {
            try (InputStream inputStream = readAccess.openInputStream(file)) {
              handler.importStream(mainFilePath, inputStream, file.getPath());
            }
          }
          model.refreshItem(type, handler.getNewId());
          messaging.addTemporaryMessage(MessageType.Information, "AnathemaCore.Tools.RepositoryView.DuplicateDoneMessage");
        }
      } catch (PersistenceException e) {
        environment.handle(e, environment.getString("AnathemaCore.Tools.RepositoryView.RepositoryError"));
      } catch (IOException e) {
        environment.handle(e, environment.getString("AnathemaCore.Tools.RepositoryView.FileError"));
      }
    });
    model.addTreeSelectionChangeListener(() -> {
      if (model.getPrintNameFilesInSelection().length == 1) {
        tool.enable();
      } else {
        tool.disable();
      }
    });
    tool.disable();
  }
}
