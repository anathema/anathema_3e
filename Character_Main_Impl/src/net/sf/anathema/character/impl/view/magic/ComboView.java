package net.sf.anathema.character.impl.view.magic;

import net.sf.anathema.character.view.magic.IComboView;
import org.jdesktop.swingx.JXTaskPane;

import javax.swing.Action;
import javax.swing.JLabel;
import java.awt.Component;

public class ComboView implements IComboView {

  private final JXTaskPane taskPaneGroup = new JXTaskPane();
  private JLabel label;
  private final Action deleteAction;
  private final Action editAction;
  private Component deleteComponent;

  public ComboView(Action deleteAction, Action editAction) {
    this.deleteAction = deleteAction;
    this.editAction = editAction;
  }

  public JXTaskPane getTaskGroup() {
    return taskPaneGroup;
  }

  @Override
  public void initGui(String name, String description) {
    label = new JLabel(description);
    taskPaneGroup.add(label);
    taskPaneGroup.add(editAction);
    deleteComponent = taskPaneGroup.add(deleteAction);
    taskPaneGroup.setTitle(name);
  }

  @Override
  public void updateCombo(String name, String description) {
    taskPaneGroup.setTitle(name);
    label.setText(description);
    net.sf.anathema.lib.gui.swing.GuiUtilities.revalidate(taskPaneGroup);
  }

  @Override
  public void setEditText(String text) {
    editAction.putValue(Action.NAME, text);
  }

  @Override
  public void setEditButtonsVisible(boolean enabled) {
    if (!enabled) {
      taskPaneGroup.remove(deleteComponent);
      net.sf.anathema.lib.gui.swing.GuiUtilities.revalidateTree(taskPaneGroup);
    } else {
      // todo vom (04.04.2005) (sieroux): Komponenten einfuegen falls noch nicht enthalten
    }
  }
}
