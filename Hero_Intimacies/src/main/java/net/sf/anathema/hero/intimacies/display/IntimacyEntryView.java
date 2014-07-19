package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.view.ObjectSelectionView;

public interface IntimacyEntryView {

  void addTextChangeListener(ObjectChangedListener<String> listener);

  Tool addTool();

  void clear();

  <T> ObjectSelectionView<T> addSelection(AgnosticUIConfiguration<T> uiConfiguration);
}