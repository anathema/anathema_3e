package net.sf.anathema.library.fx.selection;

import net.sf.anathema.library.presenter.AgnosticUIConfiguration;

public interface SelectionViewFactory {
  <T> FxObjectSelectionView<T> create(String label, AgnosticUIConfiguration<T> ui);
}