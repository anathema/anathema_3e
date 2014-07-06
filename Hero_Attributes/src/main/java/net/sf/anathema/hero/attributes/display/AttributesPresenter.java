package net.sf.anathema.hero.attributes.display;

import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.display.StatedTraitModelPresenter;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.library.fx.dot.ColumnCount;
import net.sf.anathema.library.fx.dot.GroupedStatedDotsView;
import net.sf.anathema.library.resources.Resources;

public class AttributesPresenter {

  private final StatedTraitModelPresenter presenter;

  public AttributesPresenter(Hero hero, Resources resources, GroupedStatedDotsView view) {
    AttributeModel attributeModel = AttributesModelFetcher.fetch(hero);
    IdentifiedTraitTypeList[] traitTypeGroups = attributeModel.getGroups();
    view.initGui(new ColumnCount(1));
    this.presenter = new StatedTraitModelPresenter(hero, attributeModel, view, resources);
  }

  public void initPresentation() {
    presenter.init("AttributeGroupType.Name");
  }
}
