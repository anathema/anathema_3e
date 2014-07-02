package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.display.model.CharacterCategoryCollection;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

import java.util.List;

public class CharacterAlienCharmPresenter implements AlienCharmPresenter {

  private CharmDisplayModel model;

  public CharacterAlienCharmPresenter(CharmDisplayModel model) {
    this.model = model;
  }

  @Override
  public void initPresentation(final ObjectSelectionView<CategoryReference> typeSelector) {
    model.addCasteChangeListener(() -> {
      boolean alienCharms = model.isAllowedAlienCharms();
      CharmsModel charmConfiguration = model.getCharmModel();
      if (!alienCharms) {
        charmConfiguration.forgetAllAlienCharms();
      }
      List<CategoryReference> categories = new CharacterCategoryCollection(model).getCurrentCategories();
      typeSelector.setObjects(categories.toArray(new CategoryReference[categories.size()]));
    });
  }
}
