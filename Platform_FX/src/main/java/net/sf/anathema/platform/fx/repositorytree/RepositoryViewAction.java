package net.sf.anathema.platform.fx.repositorytree;

import javafx.scene.Node;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.item.ItemTypeCollection;
import net.sf.anathema.platform.messaging.Messaging;
import net.sf.anathema.platform.repositorytree.AmountMessaging;
import net.sf.anathema.platform.repositorytree.RepositoryItemDeletionPresenter;
import net.sf.anathema.platform.repositorytree.RepositoryItemDuplicationPresenter;
import net.sf.anathema.platform.repositorytree.RepositoryItemExportPresenter;
import net.sf.anathema.platform.repositorytree.RepositoryItemImportPresenter;
import net.sf.anathema.platform.repositorytree.RepositoryMessagingPresenter;
import net.sf.anathema.platform.repositorytree.RepositoryTreeModel;
import net.sf.anathema.platform.repositorytree.RepositoryTreePresenter;
import org.controlsfx.dialog.Dialog;

public class RepositoryViewAction implements Command {
  private final ApplicationModel model;
  private final Environment environment;
  private final UiEnvironment uiEnvironment;

  public RepositoryViewAction(ApplicationModel model, Environment environment, UiEnvironment uiEnvironment) {
    this.model = model;
    this.environment = environment;
    this.uiEnvironment = uiEnvironment;
  }

  @Override
  public void execute() {
    Dialog dialog = uiEnvironment.createDialog(getTitle());
    dialog.setMasthead(createCurrentMessage());
    dialog.getActions().setAll(Dialog.Actions.OK);
    dialog.setContent(createContent());
    dialog.show();
  }


  private Node createContent() {
    RepositoryTreeView treeView = new RepositoryTreeView();
    ItemTypeCollection itemTypeCollection = new ItemTypeCollection(environment.getObjectFactory());
    RepositoryTreeModel repositoryTreeModel = new RepositoryTreeModel(model.getRepository(), itemTypeCollection);
    new RepositoryTreePresenter(environment, model,itemTypeCollection, repositoryTreeModel, treeView, "AnathemaCore.Tools.RepositoryView.TreeRoot")
            .initPresentation();
    Messaging messaging = model.getMessaging();
    AmountMessaging fileCountMessaging = new AmountMessaging(messaging, environment);
    new RepositoryItemDeletionPresenter(environment, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemExportPresenter(environment, uiEnvironment, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemImportPresenter(environment, uiEnvironment, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemDuplicationPresenter(environment, repositoryTreeModel, treeView, messaging).initPresentation();
    new RepositoryMessagingPresenter(repositoryTreeModel, messaging).initPresentation();
    return treeView.getNode();
  }

  private String createCurrentMessage() {
    return environment.getString("AnathemaCore.Tools.RepositoryView.DialogMessage");
  }

  private String getTitle() {
    return environment.getString("AnathemaCore.Tools.RepositoryView.DialogTitle");
  }
}