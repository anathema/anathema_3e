package net.sf.anathema.library.fx.selection;

import net.sf.anathema.library.presenter.AgnosticUIConfiguration;

public class ComboBoxSelectionFactory implements SelectionViewFactory {

  @Override public <T> FxObjectSelectionView<T> create(String label, AgnosticUIConfiguration<T> ui) {
    return new ComboBoxSelectionView<>(label, ui);
  }
}