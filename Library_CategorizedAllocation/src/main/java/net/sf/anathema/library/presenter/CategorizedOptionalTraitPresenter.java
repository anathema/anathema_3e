package net.sf.anathema.library.presenter;

import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.library.model.property.OptionalEntryCategory;
import net.sf.anathema.library.model.trait.OptionalTraitOption;
import net.sf.anathema.library.model.trait.OptionalTraitsModel;
import net.sf.anathema.library.model.trait.PossessedOptionalTrait;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.trait.OptionalTraitItemView;
import net.sf.anathema.library.view.trait.OptionalTraitsView;
import net.sf.anathema.platform.taskbar.BasicUi;

public class CategorizedOptionalTraitPresenter<
	C extends OptionalEntryCategory,
	O extends OptionalTraitOption,
	T extends PossessedOptionalTrait<O>>
	extends AbstractCategorizedOptionalPresenter<C, O, T> {

	private final OptionalTraitsView view;
	private final int traitMaxValue;
	
	public CategorizedOptionalTraitPresenter(OptionalTraitsModel<C, O, T> model, OptionalTraitsView view, Resources resources, int traitMaxValue) {
    super( model, view, resources);
    this.traitMaxValue = traitMaxValue;
    this.view = view;
  }
	
	protected void addSubView(T trait) {
		OptionalTraitItemView subView = view.addItemView(trait.toString(), traitMaxValue, new BasicUi().getRemoveIconPath());
		new TraitPresenter(trait, subView.getIntValueView()).initPresentation();
		viewsByEntry.put(trait, subView);
		subView.addButtonListener(() -> model.removeEntry(trait));
		subView.setEnabled(model.isRemovalAllowed(trait));
	}
}
