package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.platform.tree.display.TreeView;

import java.util.Collection;

public interface CharmView extends SpecialCharmViewContainer {

  TreeView addTreeView();

  <T> ObjectSelectionView<T> addSelectionView(String title, AgnosticUIConfiguration<T> uiConfig);

  <T> ObjectSelectionView<T> addSelectionViewAndSizeItFor(String title, AgnosticUIConfiguration<T> uiConfig,
                                                          Collection<T> objects);

  void whenCursorLeavesCharmAreaResetAllPopups();
}