package net.sf.anathema.hero.display.fx.perspective;

import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.CharacterGridView;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;
import net.sf.anathema.library.fx.tool.FxBaseTool;
import net.sf.anathema.library.fx.tool.FxButtonTool;
import net.sf.anathema.library.fx.tool.FxToggleTool;
import net.sf.anathema.library.interaction.AcceleratorMap;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.interaction.view.InteractionView;
import net.sf.anathema.library.interaction.view.MenuTool;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxCharacterNavigation implements InteractionView, CharacterGridView {
  private MigPane pane = new MigPane(withoutInsets().gridGap("0", "2").wrapAfter(1), new AC().grow().fill(), new AC().fill());
  private MigPane navigation = new MigPane(withoutInsets().gridGap("0", "2").wrapAfter(1), new AC().grow().fill(), new AC().fill());
  private ToolBar toolBar = new ToolBar();
  private final AcceleratorMap acceleratorMap;
  private final CharacterGridFxView gridView;

  public FxCharacterNavigation(UiEnvironment uiEnvironment) {
    this.acceleratorMap = uiEnvironment;
    this.gridView = new CharacterGridFxView(uiEnvironment);
    pane.add(toolBar, new CC().width("100%").grow());
    pane.add(navigation, new CC().push());
    addContainerToNavigation(gridView.getNode());
  }

  public Tool addTool() {
    FxButtonTool fxButtonTool = FxButtonTool.ForToolbar();
    addTool(fxButtonTool);
    return fxButtonTool;
  }

  @SuppressWarnings("UnusedDeclaration")  //Used by subclasses
  public ToggleTool addToggleTool() {
    final FxToggleTool fxToggleTool = FxToggleTool.create();
    addTool(fxToggleTool);
    return fxToggleTool;
  }

  protected void addContainerToNavigation(Node element) {
    navigation.add(element, new CC().push());
  }

  public void clear() {
    navigation.getChildren().clear();
  }

  public Node getNode() {
    return pane;
  }

  protected void addTool(FxBaseTool fxButtonTool) {
    toolBar.getItems().add(fxButtonTool.getNode());
    fxButtonTool.registerHotkeyIn(acceleratorMap);
  }


  @Override
  public void addButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector) {
    gridView.addButton(dto, characterSelector);
  }

  @Override
  public void selectButton(CharacterIdentifier identifier) {
    gridView.selectButton(identifier);
  }

  @Override
  public void updateButton(CharacterButtonDto dto) {
    gridView.updateButton(dto);
  }

  @Override
  public CharacterTemplateCreator createNewCharacter() {
    return gridView.createNewCharacter();
  }

  @Override
  public MenuTool addMenuTool() {
    FxMenuButtonTool tool = FxMenuButtonTool.ForToolbar();
    addTool(tool);
    return tool;
  }
}
