package net.sf.anathema.hero.specialties.display.view;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;
import javafx.scene.Node;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtiesConfigurationView;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtyCreationView;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;

import org.tbee.javafx.scene.layout.MigPane;

public class FxSpecialtiesView implements SpecialtiesConfigurationView, NodeHolder {
  private final MigPane pane = new MigPane(LayoutUtils.withoutInsets().wrapAfter(1));
  private final MigPane creationPane = new MigPane(LayoutUtils.withoutInsets());
  private final MigPane existingSpecialtiesPane = new MigPane(withoutInsets().wrapAfter(2).fill());

  public FxSpecialtiesView() {
    pane.add(creationPane);
    pane.add(existingSpecialtiesPane);
  }

  @Override
  public SpecialtyView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon) {
  	FxSpecialtyView view = new FxSpecialtyView(deleteIcon, abilityName + " - " + specialtyName);
    view.addTo(existingSpecialtiesPane);
    return view;
  }

  @Override
  public SpecialtyCreationView addSpecialtyCreationView(AgnosticUIConfiguration<TraitType> configuration, RelativePath addIcon) {
    final FxSpecialtyCreationView view = new FxSpecialtyCreationView(configuration, addIcon);
    creationPane.add(view.getNode());
    return view;
  }

  @Override
  public Node getNode() {
    return pane;
  }
}