package net.sf.anathema.hero.specialties.display.view;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtyCreationView;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.fx.selection.ComboBoxSelectionView;
import net.sf.anathema.library.fx.tool.FxButtonTool;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxSpecialtyCreationView implements SpecialtyCreationView {

  private final ComboBoxSelectionView<TraitType> box;
  private final TextField field = new TextField();
  private final FxButtonTool tool = FxButtonTool.ForToolbar();
  private final MigPane pane = new MigPane(withoutInsets());

  public FxSpecialtyCreationView(AgnosticUIConfiguration<TraitType> configuration, RelativePath addIcon) {
    this.box = new ComboBoxSelectionView<>("", configuration);
    pane.add(box.getNode());
    pane.add(field);
    pane.add(tool.getNode(), new CC().alignY("center"));
    tool.setIcon(addIcon);
  }

  @Override
  public void addSelectionChangedListener(final ObjectChangedListener<TraitType> listener) {
    box.addObjectSelectionChangedListener(listener);
  }

  @Override
  public void addEditChangedListener(final ObjectChangedListener<String> listener) {
    field.textProperty().addListener((observableValue, s, s2) -> listener.valueChanged(s2));
  }

  @Override
  public void whenAddButtonIsClicked(Command command) {
    tool.setCommand(command);
  }

  @Override
  public void clear() {
    box.clearSelection();
    field.textProperty().setValue(null);
  }

  @Override
  public void setButtonEnabled(boolean enabled) {
    if (enabled) {
      tool.enable();
    } else {
      tool.disable();
    }
  }

  @Override
  public void setObjects(Collection<TraitType> references) {
    box.setObjects(references);
  }

  @Override
  public void selectTrait(final TraitType currentTrait) {
    box.setSelectedObject(currentTrait);
  }

  @Override
  public void enterName(final String currentName) {
    field.setText(currentName);
  }

  public Node getNode() {
    return pane;
  }
}