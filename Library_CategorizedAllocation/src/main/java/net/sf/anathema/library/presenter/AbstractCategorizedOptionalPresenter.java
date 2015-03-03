package net.sf.anathema.library.presenter;

import net.sf.anathema.hero.experience.model.ExperienceChange;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.model.OptionalEntriesModel;
import net.sf.anathema.library.model.OptionalEntryCategory;
import net.sf.anathema.library.model.PossessedOptionalEntry;
import net.sf.anathema.library.model.RemovableEntryListener;
import net.sf.anathema.library.model.property.OptionalPropertyOption;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.library.view.OptionalEntriesView;
import net.sf.anathema.library.view.OptionalEntryItemView;
import net.sf.anathema.library.view.OptionalPropertyEntryView;
import net.sf.anathema.platform.taskbar.BasicUi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCategorizedOptionalPresenter<
C extends OptionalEntryCategory,
O extends OptionalPropertyOption,
T extends PossessedOptionalEntry<O>> {

	protected ObjectSelectionView<O> traitBox;
	protected ITextView textBox;
	protected final OptionalEntriesView view;
	protected final Resources resources;
	protected final Map<T, OptionalEntryItemView> viewsByEntry = new HashMap<>();
	protected final OptionalEntriesModel<C, O, T> model;
	private final Hero hero;

	public AbstractCategorizedOptionalPresenter(Hero hero,
			OptionalEntriesModel<C, O, T> model,
			OptionalEntriesView view,
			Resources resources) {
		this.hero = hero;
		this.model = model;
		this.view = view;
		this.resources = resources;
	}

	public void initPresentation() {
		OptionalPropertyEntryView selectionView = view.addSelectionView();
		Tool tool = initCreationView(selectionView);
		initModelListening(selectionView, tool);
		for (T trait : model.getPossessedEntries()) {
			addSubView(trait);
		}
		reset();
		List<Trait> contingentTraits = model.getContingentTraits();
		for (Trait trait : contingentTraits) {
			trait.addCurrentValueListener(value -> refreshTraitList());
		}
		
		hero.getChangeAnnouncer().addListener(flavor -> {
      if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
        updateRemovalStatus();
      }
    });
	}
	
	private void updateRemovalStatus() {
		for (T entry : model.getEntries()) {
			viewsByEntry.get(entry).setEnabled(model.isRemovalAllowed(entry));
		}
	}

	protected abstract void addSubView(T trait);

	private void initModelListening(OptionalPropertyEntryView selectionView, Tool tool) {
		model.addModelChangeListener(new RemovableEntryListener<T>() {
			@Override
			public void entryAdded(T trait) {
				addSubView(trait);
				reset();
			}

			@Override
			public void entryRemoved(T trait) {
				OptionalEntryItemView itemView = viewsByEntry.get(trait);
				itemView.remove();
			}

			@Override
			public void entryAllowed(boolean complete) {
				if (complete) {
					tool.enable();
				} else {
					tool.disable();
				}
			}
		});
	}

	private Tool initCreationView(OptionalPropertyEntryView selectionView) {
		if (model.hasCategories()) {
			addCategorySelection(selectionView);
		}
		this.traitBox = addTraitSelection(selectionView);
		addDescriptionBox(selectionView);
		return addCommitTool(selectionView);
	}

	private void addCategorySelection(OptionalPropertyEntryView selectionView) {
		ObjectSelectionView<C> typeView = selectionView.addSelection(new ToStringConfiguration<>());
		typeView.setObjects(model.getAvailableCategories());
		typeView.setSelectedObject(model.getCurrentCategory());
		typeView.addObjectSelectionChangedListener(model::setCurrentCategory);
		model.whenCategoryChanges(() -> {
			typeView.setSelectedObject(model.getCurrentCategory());
			refreshTraitList();
		});
	}

	private ObjectSelectionView<O> addTraitSelection(OptionalPropertyEntryView selectionView) {
		ObjectSelectionView<O> traitView = selectionView.addSelection(new ToStringConfiguration<>());
		traitView.setObjects(model.getCurrentEntryOptions());
		traitView.setSelectedObject(model.getSelectedEntryOption());
		traitView.addObjectSelectionChangedListener(model::setSelectedEntryOption);
		model.whenSelectedOptionChanges(() -> {
			traitView.setSelectedObject(model.getSelectedEntryOption());
			textBox.suggestCompletions(model.getSuggestedDescriptions());
		});
		return traitView;
	}

	private void addDescriptionBox(OptionalPropertyEntryView selectionView) {
		this.textBox = selectionView.addDescriptionBox(resources.getString("Merits.DescriptionLabel"));
		textBox.addTextChangedListener(model::setCurrentDescription);
	}

	private Tool addCommitTool(OptionalPropertyEntryView selectionView) {
		Tool tool = selectionView.addTool();
		tool.setIcon(new BasicUi().getAddIconPath());
		tool.setCommand(model::commitSelection);
		return tool;
	}

	private void reset() {
		traitBox.clearSelection();
		textBox.clear();
		model.resetCurrentEntry();
	}

	private void refreshTraitList() {
		traitBox.setObjects(model.getCurrentEntryOptions());
		model.selectFirstEntryOption();
	}

}
