package net.sf.anathema.library.presenter;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.model.OptionalEntryCategory;
import net.sf.anathema.library.model.PossessedOptionalEntry;
import net.sf.anathema.library.model.property.OptionalPropertiesModel;
import net.sf.anathema.library.model.property.OptionalPropertyOption;
import net.sf.anathema.library.model.property.PossessedOptionalProperty;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.property.OptionalPropertiesView;
import net.sf.anathema.library.view.property.OptionalPropertyItemView;
import net.sf.anathema.platform.taskbar.BasicUi;

public class CategorizedOptionalPropertyPresenter extends AbstractCategorizedOptionalPresenter {
	
	private final OptionalPropertiesView view;

	public CategorizedOptionalPropertyPresenter(Hero hero,
			OptionalPropertiesModel<?, ?> model, OptionalPropertiesView view,
			Resources resources) {
		super(hero, model, view, resources);
		this.view = view;
	}

	@Override
	protected void addSubView(PossessedOptionalEntry<?> entry) {
    RelativePath deleteIcon = new BasicUi().getRemoveIconPath();
    OptionalPropertyItemView itemView = view.addItemView(entry.toString(), deleteIcon);
    itemView.addButtonListener(() -> model.removeEntry(entry));
    viewsByEntry.put(entry, itemView);
	}
}