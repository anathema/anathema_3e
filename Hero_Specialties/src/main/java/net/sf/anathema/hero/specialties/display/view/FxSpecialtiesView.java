package net.sf.anathema.hero.specialties.display.view;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.display.fx.dot.ExtensibleDotView;
import net.sf.anathema.hero.display.fx.dot.FxDotView;
import net.sf.anathema.hero.display.fx.dot.FxExtensibleDotView;
import net.sf.anathema.hero.display.fx.dot.SimpleDotViewPanel;
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
  private final SimpleDotViewPanel existingSpecialtiesPane = new SimpleDotViewPanel();

  public FxSpecialtiesView() {
    pane.add(creationPane);
    pane.add(existingSpecialtiesPane.getNode(), new CC().grow().pushY());
  }

  @Override
  public ExtensibleDotView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon, int value, int maxValue) {
    FxDotView view = FxDotView.WithDefaultLayout(abilityName + " - " + specialtyName, maxValue);
    FxExtensibleDotView extensibleTraitView = new FxExtensibleDotView(view);
    extensibleTraitView.addTo(existingSpecialtiesPane);
    return extensibleTraitView;
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