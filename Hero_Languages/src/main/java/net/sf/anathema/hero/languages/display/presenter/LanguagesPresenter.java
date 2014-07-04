package net.sf.anathema.hero.languages.display.presenter;

import net.sf.anathema.hero.framework.display.labelledvalue.LabelledAllotmentView;
import net.sf.anathema.hero.framework.library.overview.OverviewCategory;
import net.sf.anathema.hero.framework.library.removableentry.RemovableEntryListener;
import net.sf.anathema.hero.languages.model.LanguagesModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.legality.LegalityColorProvider;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.RemovableEntryView;
import net.sf.anathema.library.view.StyledValueView;
import net.sf.anathema.platform.taskbar.BasicUi;

import java.util.HashMap;
import java.util.Map;

public class LanguagesPresenter {

  private final LanguagesModel model;
  private final LanguagesView view;
  private final Resources resources;
  private final Map<Identifier, RemovableEntryView> viewsByEntry = new HashMap<>();
  private final Map<String, Identifier> languagesByDisplayName = new HashMap<>();

  public LanguagesPresenter(LanguagesModel model, LanguagesView view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    initEntryPresentation();
    initPointPresentation();
  }

  private void initPointPresentation() {
    OverviewCategory overview = view.addOverview(resources.getString("Linguistics.Overview.Border"));
    final StyledValueView<Integer> familyView = overview.addIntegerValueView(
            resources.getString("Linguistics.Overview.Families"), 1);
    final StyledValueView<Integer> barbarianView = overview.addIntegerValueView(
            resources.getString("Linguistics.Overview.Barbarian"), 2);
    final LabelledAllotmentView totalView = overview.addAlotmentView(
            resources.getString("Linguistics.Overview.Total"), 2);
    model.addModelChangeListener(new RemovableEntryListener<Identifier>() {
      @Override
      public void entryAdded(Identifier entry) {
        updateOverview(familyView, totalView, barbarianView);
      }

      @Override
      public void entryAllowed(boolean complete) {
        //nothing to do;
      }

      @Override
      public void entryRemoved(Identifier entry) {
        updateOverview(familyView, totalView, barbarianView);
      }
    });
    model.addCharacterChangedListener(() -> updateOverview(familyView, totalView, barbarianView));
    updateOverview(familyView, totalView, barbarianView);
  }

  private void updateOverview(
          StyledValueView<Integer> familyView,
          LabelledAllotmentView totalView,
          StyledValueView<Integer> barbarianView) {
    familyView.setValue(model.getPredefinedLanguageCount());
    barbarianView.setValue(model.getBarbarianLanguageCount());
    int pointsSpent = model.getLanguagePointsSpent();
    totalView.setValue(pointsSpent);
    int pointsAllowed = model.getLanguagePointsAllowed();
    totalView.setAllotment(pointsAllowed);
    if (pointsSpent > pointsAllowed) {
      totalView.setTextColor(LegalityColorProvider.COLOR_HIGH);
    } else {
      totalView.setTextColor(LegalityColorProvider.COLOR_OKAY);
    }
  }

  @SuppressWarnings("serial")
  private void initEntryPresentation() {
    String labelText = resources.getString("Linguistics.SelectionView.Label");
    AgnosticUIConfiguration<Object> uiConfiguration = new LanguageUiConfiguration();
    final ObjectSelectionViewWithTool<Object> selectionView = view.addSelectionView(labelText, uiConfiguration);
    selectionView.setObjects(model.getPredefinedLanguages());
    selectionView.addObjectSelectionChangedListener(newValue -> {
      if (newValue == null) {
        return;
      }
      Identifier definedLanguage = getLanguage(newValue);
      if (definedLanguage == null) {
        model.selectBarbarianLanguage(newValue.toString());
      } else {
        model.selectLanguage(definedLanguage);
      }
    });
    final Tool addButton = selectionView.addTool();
    addButton.setIcon(new BasicUi().getAddIconPath());
    addButton.setCommand(() -> {
      if (!model.isEntryAllowed()) {
        return;
      }
      model.commitSelection();
    });
    model.addModelChangeListener(new RemovableEntryListener<Identifier>() {
      @Override
      public void entryAdded(Identifier entry) {
        addEntry(entry);
        selectionView.setSelectedObject(null);
      }

      @Override
      public void entryAllowed(boolean complete) {
        selectionView.setSelectedObject(model.getSelectedEntry());
        if (complete) {
          addButton.enable();
        } else {
          addButton.disable();
        }
      }

      @Override
      public void entryRemoved(Identifier entry) {
        RemovableEntryView entryView = viewsByEntry.remove(entry);
        entryView.delete();
      }
    });
    for (Identifier language : model.getPredefinedLanguages()) {
      languagesByDisplayName.put(getDisplayString(language), language);
    }
    for (Identifier language : model.getEntries()) {
      addEntry(language);
    }
  }

  private void addEntry(final Identifier language) {
    RelativePath removeIcon = new BasicUi().getRemoveIconPath();
    RemovableEntryView entryView = view.addEntryView(removeIcon, getDisplayString(language));
    viewsByEntry.put(language, entryView);
    entryView.addButtonListener(() -> model.removeEntry(language));
  }

  private Identifier getLanguage(Object anObject) {
    if (anObject instanceof Identifier) {
      return (Identifier) anObject;
    }
    String displayName = anObject.toString();
    Identifier language = languagesByDisplayName.get(displayName);
    if (language != null) {
      return language;
    }
    return model.getPredefinedLanguageById(displayName);
  }

  private String getDisplayString(Object object) {
    if (object == null) {
      return null;
    }
    if (model.isPredefinedLanguage(object)) {
      return resources.getString("Language." + ((Identifier) object).getId());
    }
    return object.toString();
  }

  private class LanguageUiConfiguration extends AbstractUIConfiguration<Object> {
    @Override
    public String getLabel(Object value) {
      return getDisplayString(value);
    }
  }
}
