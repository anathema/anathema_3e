package net.sf.anathema.equipment.editor.view;

import java.util.List;

import net.sf.anathema.library.collection.Closure;
import net.sf.anathema.library.interaction.model.Tool;

public interface ToolListView<T> {
  void setObjects(List<T> items);

  void addListSelectionListener(Runnable listener);

  void addListSelectionListener(Closure<T> listener);

  List<T> getSelectedItems();

  Tool addTool();
}