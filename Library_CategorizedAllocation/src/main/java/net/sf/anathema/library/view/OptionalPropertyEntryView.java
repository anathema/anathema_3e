package net.sf.anathema.library.view;

import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.ObjectSelectionView;

public interface OptionalPropertyEntryView {
  Tool addTool();

  ITextView addDescriptionBox(String label);

  <T> ObjectSelectionView<T> addSelection(AgnosticUIConfiguration<T> uiConfiguration);
}
