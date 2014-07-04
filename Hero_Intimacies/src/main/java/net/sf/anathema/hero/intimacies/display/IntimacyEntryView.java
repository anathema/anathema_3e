package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.library.event.ObjectValueListener;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.ObjectSelectionView;

public interface IntimacyEntryView {

  void addTextChangeListener(ObjectValueListener<String> listener);

  Tool addTool();

  void clear();

  <T> ObjectSelectionView<T> addSelection();
}