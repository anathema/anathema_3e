package net.sf.anathema.character.equipment.item.view.fx;

import net.sf.anathema.character.equipment.item.view.AgnosticEquipmentDatabaseView;
import net.sf.anathema.library.fx.selection.ComboBoxSelectionFactory;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.fx.utility.UtilityPane;

public class FxEquipmentDatabaseView {

  public final UtilityPane utilityPane = new UtilityPane("skin/equipment/equipment.css", "skin/platform/dotselector.css");
  private final FxEquipmentNavigation navigation;
  private final FxEquipmentDetails details;
  public final AgnosticEquipmentDatabaseView view;

  public FxEquipmentDatabaseView(UiEnvironment uiEnvironment) {
    this.details = new FxEquipmentDetails(new ComboBoxSelectionFactory(), uiEnvironment);
    this.navigation = new FxEquipmentNavigation(uiEnvironment);
    this.view = new AgnosticEquipmentDatabaseView(navigation, details);
    initializePerspective();
  }

  private void initializePerspective() {
    utilityPane.addStyleSheetClass("equipment-utility");
    utilityPane.setNavigationComponent(navigation.getNode());
    utilityPane.setContentComponent(details.getNode());
  }
}