package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.library.view.ObjectSelectionView;

public class NullAlienCharmPresenter implements AlienCharmPresenter {
  @Override
  public void initPresentation(ObjectSelectionView<CategoryReference> typeSelector) {
    //nothing to do
  }
}
