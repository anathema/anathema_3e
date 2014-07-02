package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

public interface IntimacyEntryView {

  void addTextChangeListener(ObjectValueListener<String> listener);

  Tool addTool();

  void clear();

  <T> ObjectSelectionView<T> addSelection();
}