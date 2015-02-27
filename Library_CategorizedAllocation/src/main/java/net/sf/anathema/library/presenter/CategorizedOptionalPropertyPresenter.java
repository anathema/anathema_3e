package net.sf.anathema.library.presenter;

import net.sf.anathema.library.model.property.OptionalPropertiesModel;
import net.sf.anathema.library.model.property.OptionalEntryCategory;
import net.sf.anathema.library.model.property.OptionalPropertyOption;
import net.sf.anathema.library.model.property.PossessedOptionalProperty;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.property.OptionalPropertiesView;
import net.sf.anathema.library.view.property.OptionalPropertyItemView;
import net.sf.anathema.platform.taskbar.BasicUi;

public class CategorizedOptionalPropertyPresenter<
	C extends OptionalEntryCategory,
	O extends OptionalPropertyOption,
	T extends PossessedOptionalProperty<O>> 
	extends AbstractCategorizedOptionalPresenter<C, O, T> {
	
	private final OptionalPropertiesView view;

	public CategorizedOptionalPropertyPresenter(
			OptionalPropertiesModel<C, O, T> model, OptionalPropertiesView view,
			Resources resources) {
		super(model, view, resources);
		this.view = view;
	}

	@Override
	protected void addSubView(T entry) {
    RelativePath deleteIcon = new BasicUi().getRemoveIconPath();
    OptionalPropertyItemView itemView = view.addItemView(entry.toString(), deleteIcon);
    itemView.addButtonListener(() -> model.removeEntry(entry));
    viewsByEntry.put(entry, itemView);
	}
}