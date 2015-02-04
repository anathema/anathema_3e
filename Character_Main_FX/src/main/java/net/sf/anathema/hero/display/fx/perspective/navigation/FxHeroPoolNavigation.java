package net.sf.anathema.hero.display.fx.perspective.navigation;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.HeroesGridView;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.library.fx.Stylesheet;
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

public class FxHeroPoolNavigation implements InteractionView, HeroesGridView {
  private final MigPane content = new MigPane(withoutInsets().gridGap("2", "0"), new AC().fill(), new AC().fill());
  private final HeroPoolFxView heroes = new HeroPoolFxView();
  private final HBox frontTools = new HBox();
  private final HBox backTools = new HBox();
  private final ToolBar toolBar = new ToolBar();
  private final AcceleratorMap acceleratorMap;

  public FxHeroPoolNavigation(UiEnvironment uiEnvironment) {
    this.acceleratorMap = uiEnvironment;
    new Stylesheet("skin/character/characternavigation.css").applyToParent(content);
    content.add(heroes.getNode());
    content.add(frontTools, new CC().push().grow());
    content.add(backTools, new CC().dockEast());
    backTools.getChildren().add(toolBar);
    content.getStyleClass().add("selection-pane");
    toolBar.getStyleClass().add("toolbox");
  }

  public Tool addTool() {
    FxButtonTool fxButtonTool = FxButtonTool.ForToolbar();
    addTool(fxButtonTool);
    return fxButtonTool;
  }

  public ToggleTool addToggleTool() {
    FxToggleTool fxToggleTool = FxToggleTool.create();
    addTool(fxToggleTool);
    return fxToggleTool;
  }

  public void clear() {
    heroes.clear();
  }

  public Node getNode() {
    return content;
  }

  protected void addTool(FxBaseTool fxButtonTool) {
    toolBar.getItems().add(fxButtonTool.getNode());
    fxButtonTool.registerHotkeyIn(acceleratorMap);
  }
  
  public Tool createBigToolAtTheEnd() {
    HeroPoolTool tool = HeroPoolTool.createTool(new Button());
    backTools.getChildren().add(tool.getNode());
    return tool;
  }

  @Override
  public void addButton(CharacterButtonDto dto, Selector<HeroIdentifier> characterSelector) {
    heroes.addButton(dto, characterSelector);
  }

  @Override
  public void selectButton(HeroIdentifier identifier) {
    heroes.selectButton(identifier);
  }

  @Override
  public void updateButton(CharacterButtonDto dto) {
    heroes.updateButton(dto);
  }

  @Override
  public CharacterTemplateCreator createNewCharacter(Tool caller) {
    return heroes.createNewCharacter(caller);
  }

  @Override
  public MenuTool addMenuTool() {
    FxMenuButtonTool tool = FxMenuButtonTool.ForToolbar();
    addTool(tool);
    return tool;
  }

  public InteractionView getFrontInteraction() {
    return new HeroInteraction(frontTools);
  }
}