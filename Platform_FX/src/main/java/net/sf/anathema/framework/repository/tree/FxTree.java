package net.sf.anathema.framework.repository.tree;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import net.sf.anathema.library.collection.Closure;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.platform.fx.ConfigurableTreeCellFactory;
import net.sf.anathema.platform.repositorytree.AgnosticTree;
import net.sf.anathema.platform.repositorytree.AgnosticTreeNode;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class FxTree implements AgnosticTree {
  private final TreeView<Object> treeView;
  private final Announcer<Closure> announcer = Announcer.to(Closure.class);

  public FxTree(TreeView<Object> treeView) {
    this.treeView = treeView;
    treeView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TreeItem<Object>>() {
      @Override
      public void onChanged(Change<? extends TreeItem<Object>> change) {
        List<Object> selectedValues = new ArrayList<>();
        ObservableList<TreeItem<Object>> selectedItems = treeView.getSelectionModel().getSelectedItems();
        for (TreeItem<Object> selectedItem : selectedItems) {
          selectedValues.add(selectedItem.getValue());
        }
        announcer.announce().execute(selectedValues.toArray(new Object[selectedValues.size()]));
      }
    });
  }

  @Override
  public void setUiConfiguration(AgnosticUIConfiguration configuration) {
    treeView.setCellFactory(new ConfigurableTreeCellFactory<>(configuration));
  }

  @Override
  public AgnosticTreeNode createRootNode(String rootText) {
    TreeItem<Object> rootItem = new TreeItem<>();
    treeView.setRoot(rootItem);
    rootItem.setValue(rootText);
    return new FxTreeNode(rootItem);
  }

  @Override
  public void whenSelectionChangesDo(Closure<Object[]> closure) {
    announcer.addListener(closure);
  }
}
