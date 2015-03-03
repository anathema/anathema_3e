package net.sf.anathema.library.view.fx;

import javafx.scene.Node;

import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.model.trait.OptionalTraitOption;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.view.OptionalPropertyEntryView;
import net.sf.anathema.library.view.property.OptionalPropertiesView;
import net.sf.anathema.library.view.property.OptionalPropertyItemView;

import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxOptionalPropertiesView<O extends OptionalTraitOption>
	implements OptionalPropertiesView, NodeHolder {
  
  private final MigPane pane = new MigPane(LayoutUtils.withoutInsets().wrapAfter(1));
  private final MigPane creationPane = new MigPane(LayoutUtils.withoutInsets());
  private final MigPane existingSpecialtiesPane = new MigPane(withoutInsets().wrapAfter(2).fill());

  public FxOptionalPropertiesView() {
    pane.add(creationPane);
    pane.add(existingSpecialtiesPane);
  }

  @Override
  public OptionalPropertyItemView addItemView(String label, RelativePath deleteIcon) {
    FxOptionalPropertyItemView view = new FxOptionalPropertyItemView(deleteIcon, label);
    view.addTo(existingSpecialtiesPane);
    return view;
  }

  @Override
  public OptionalPropertyEntryView  addSelectionView() {
    FxOptionalEntriesInputView view = new FxOptionalEntriesInputView();
    creationPane.add(view.getNode());
    return view;
  }

  @Override
  public Node getNode() {
    return pane;
  }
}