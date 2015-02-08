package net.sf.anathema.hero.display.fx.perspective.navigation;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.UpdatingHeroesGridView;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.library.fx.Stylesheet;
import net.sf.anathema.library.fx.tool.FxBaseTool;
import net.sf.anathema.library.interaction.AcceleratorMap;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.interaction.view.InteractionView;
import net.sf.anathema.library.interaction.view.MenuTool;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxHeroPoolNavigation implements InteractionView, UpdatingHeroesGridView {
  private final MigPane content = new MigPane(withoutInsets().gridGap("2", "0"), new AC().fill().shrinkPrio(200), new AC().fill());
  private final HeroPoolFxView heroes = new HeroPoolFxView();
  private final HBox westTools = new HBox();
  private final HBox centerTools = new HBox();
  private final HBox eastTools = new HBox();
  private final HBox toolBar = new HBox();
  private final AcceleratorMap acceleratorMap;

  public FxHeroPoolNavigation(UiEnvironment uiEnvironment) {
    this.acceleratorMap = uiEnvironment;
    new Stylesheet("skin/character/characternavigation.css").applyToParent(content);
    new Stylesheet("skin/character/charactergridbutton.css").applyToParent(content);
    content.add(westTools);
    content.add(heroes.getNode());
    content.add(centerTools, new CC().push().grow());
    content.add(eastTools, new CC().dockEast());
    eastTools.getChildren().add(toolBar);
    content.getStyleClass().add("selection-pane");
  }

  public Tool addTool() {
    HeroPoolTool tool = HeroPoolTool.createTool(new Button());
    addTool(tool);
    return tool;
  }

  public ToggleTool addToggleTool() {
    HeroPoolToggleTool toggleTool = new HeroPoolToggleTool();
    addTool(toggleTool);
    return toggleTool;
  }

  public Tool createBigToolAtEastSide() {
    HeroPoolTool tool = HeroPoolTool.createTool(new Button());
    eastTools.getChildren().add(tool.getNode());
    return tool;
  }

  public void clear() {
    heroes.clear();
  }

  public Node getNode() {
    return content;
  }

  protected void addTool(FxBaseTool tool) {
    Node node = tool.getNode();
    node.getStyleClass().add("tool");
    toolBar.getChildren().add(node);
    tool.registerHotkeyIn(acceleratorMap);
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

  public InteractionView getCenterInteraction() {
    return new HeroInteraction(centerTools);
  }

  public InteractionView getWestInteraction() {
    return new HeroInteraction(westTools);
  }
}