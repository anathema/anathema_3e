package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.platform.tree.display.TreeView;

public interface CharmView extends SpecialCharmViewContainer {

  TreeView addTreeView();

  <T> ObjectSelectionView<T> addSelectionView(String title, AgnosticUIConfiguration<T> uiConfig);

  <T> ObjectSelectionView<T> addSelectionViewAndSizeItFor(String title, AgnosticUIConfiguration<T> uiConfig,
                                                          T[] objects);

  void whenCursorLeavesCharmAreaResetAllPopups();
}