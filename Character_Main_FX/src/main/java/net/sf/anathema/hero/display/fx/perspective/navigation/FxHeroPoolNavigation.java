package net.sf.anathema.hero.display.fx.perspective.navigation;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.UpdatingHeroesGridView;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.library.fx.Stylesheet;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.interaction.view.InteractionView;

import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxHeroPoolNavigation implements UpdatingHeroesGridView {
  private final MigPane content = new MigPane(withoutInsets().gridGap("2", "0"), new AC().fill().shrinkPrio(200), new AC().fill());
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final HeroPoolFxView heroes = new HeroPoolFxView(toggleGroup);
  private final HBox westTools = new HBox();
  private final HBox centerTools = new HBox();
  private final HBox eastTools = new HBox();


  public FxHeroPoolNavigation() {
    new Stylesheet("skin/character/characternavigation.css").applyToParent(content);
    new Stylesheet("skin/character/charactergridbutton.css").applyToParent(content);
    content.add(westTools);
    content.add(heroes.getNode());
    content.add(centerTools, new CC().push().grow());
    content.add(eastTools, new CC().dockEast());
    content.getStyleClass().add("selection-pane");
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
  public void clearAllButtons() {
    heroes.clearAllButtons();
  }

  public InteractionView getCenterInteraction() {
    return new HeroInteraction(centerTools, toggleGroup);
  }

  public InteractionView getWestInteraction() {
    return new HeroInteraction(westTools, toggleGroup);
  }
}