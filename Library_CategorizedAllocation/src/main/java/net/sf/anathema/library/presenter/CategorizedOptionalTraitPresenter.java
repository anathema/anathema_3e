package net.sf.anathema.library.presenter;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.model.PossessedOptionalEntry;
import net.sf.anathema.library.model.trait.OptionalTraitsModel;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.trait.OptionalTraitItemView;
import net.sf.anathema.library.view.trait.OptionalTraitsView;
import net.sf.anathema.platform.taskbar.BasicUi;

public class CategorizedOptionalTraitPresenter extends AbstractCategorizedOptionalPresenter {

	private final OptionalTraitsView view;
	private final int traitMaxValue;
	
	public CategorizedOptionalTraitPresenter(Hero hero, OptionalTraitsModel<?, ?> model, OptionalTraitsView view, Resources resources, int traitMaxValue) {
    super(hero, model, view, resources);
    this.traitMaxValue = traitMaxValue;
    this.view = view;
  }
	
	protected void addSubView(PossessedOptionalEntry<?> trait) {
		OptionalTraitItemView subView = view.addItemView(trait.toString(), traitMaxValue, new BasicUi().getRemoveIconPath());
		new TraitPresenter((Trait)trait, subView.getIntValueView()).initPresentation();
		viewsByEntry.put(trait, subView);
		subView.addButtonListener(() -> model.forget(trait));
		subView.setEnabled(model.isRemovalAllowed(trait));
	}
}
