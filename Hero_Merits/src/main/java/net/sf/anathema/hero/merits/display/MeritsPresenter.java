package net.sf.anathema.hero.merits.display;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritCategory;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.model.RemovableEntryListener;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.platform.taskbar.BasicUi;

public class MeritsPresenter {

  private MeritEntryView selectionView;
  private final MeritsView view;
  private final Resources resources;
  private final MeritsModel model;
  
  private final Map<Merit, MeritItemView> viewsByEntry = new HashMap<>();

  public MeritsPresenter(MeritsModel model, MeritsView view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    
    selectionView = view.addSelectionView();
    Tool tool = initCreationViewListening(selectionView);
    initModelListening(selectionView, tool);
    for (Merit merit : model.getMerits()) {
      addSubView(merit);
    }
    reset(selectionView);
  }

  private void addSubView(Merit merit) {
    MeritItemView subView = view.addMerit(merit.toString(), new BasicUi().getRemoveIconPath());
    new TraitPresenter(merit, subView.getIntValueView()).initPresentation();
    viewsByEntry.put(merit, subView);
    subView.addButtonListener(() -> model.removeEntry(merit));
  }

  protected void initModelListening(final MeritEntryView selectionView, final Tool tool) {
    model.addModelChangeListener(new RemovableEntryListener<Merit>() {
      @Override
      public void entryAdded(Merit merit) {
        addSubView(merit);
        reset(selectionView);
      }

      @Override
      public void entryRemoved(Merit merit) {
        MeritItemView itemView = viewsByEntry.get(merit);
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

  private Tool initCreationViewListening(MeritEntryView selectionView) {
	String labelText = resources.getString("Merits.DescriptionLabel");
	ObjectSelectionView<MeritCategory> categories = addGenericSelection(selectionView, MeritCategory.values(), model::setCurrentType, model.getCurrentType(), new MeritUiConfiguration<MeritCategory>(resources));
	ObjectSelectionView<String> option = addMeritSelection(selectionView, model.getCurrentMeritOptionLabels().toArray(new String[0]), model::setCurrentMerit,
			model.getCurrentMeritOption() != null ? model.getCurrentMeritOption().getId() : null, new MeritUiConfiguration<String>(resources));
    
	// We ideally want a text changed listener rather than an object change
	option.addObjectSelectionChangedListener(text -> model.setCurrentMerit(text));
	categories.addObjectSelectionChangedListener(item -> option.setObjects(model.getCurrentMeritOptionLabels()));
	selectionView.addDescriptionBox(labelText);
    selectionView.addTextChangeListener(model::setCurrentMerit);
    Tool tool = selectionView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setCommand(model::commitSelection);
    return tool;
  }
  
  private <T> ObjectSelectionView<T> addMeritSelection(MeritEntryView selectionView, T[] objects, ObjectChangedListener<T> listener, T initial, AgnosticUIConfiguration<T> uiConfiguration) {
	  return allowSelection(selectionView.addMeritSelection(uiConfiguration), objects, listener, initial);
  }
  
  private <T> ObjectSelectionView<T> addGenericSelection(MeritEntryView selectionView, T[] objects, ObjectChangedListener<T> listener, T initial, AgnosticUIConfiguration<T> uiConfiguration) {
	  return allowSelection(selectionView.addSelection(uiConfiguration), objects, listener, initial);
  }

  private <T> ObjectSelectionView<T> allowSelection(ObjectSelectionView<T> selection, T[] objects, ObjectChangedListener<T> listener, T initial) {
    selection.setObjects(objects);
    selection.setSelectedObject(initial);
    selection.addObjectSelectionChangedListener(listener);
    return selection;
  }

  private void reset(MeritEntryView selectionView) {
    selectionView.clear();
    model.setCurrentMerit("");
    model.setCurrentDescription("");
  }

  private class MeritUiConfiguration<T> extends AbstractUIConfiguration<T> {
	  	private final Resources resources;
	  	
	  	public MeritUiConfiguration(Resources resources) {
	  		this.resources = resources;
	  	}
	  
	    @Override
	    public String getLabel(T value) {
	      return value != null ? value.toString() : null;
	    }
	  }
}