package net.sf.anathema.hero.display.fx.perspective.content;

import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.hero.application.SubViewRegistry;
import net.sf.anathema.hero.display.fx.perspective.content.layout.RasterLayoutMap;
import net.sf.anathema.hero.display.fx.perspective.navigation.FxMenuButtonTool;
import net.sf.anathema.hero.individual.view.HeroView;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.Stylesheet;
import net.sf.anathema.library.fx.tool.FxBaseTool;
import net.sf.anathema.library.fx.view.FxStack;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.interaction.AcceleratorMap;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.interaction.view.InteractionView;
import net.sf.anathema.library.interaction.view.MenuTool;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class TopBarHeroView implements HeroView, NodeHolder {

  private MigPane content = new MigPane(withoutInsets().wrapAfter(1), new AC().index(0).shrink().shrinkPrio(200));
  private final SubViewRegistry subViewFactory;
  private final RasterLayoutMap layoutMap;
  private final BorderPane navigationBar = new BorderPane();
  private final MigPane sectionBar = new MigPane(withoutInsets().gridGap("10", "0"));
  private final MigPane interactionBar = new MigPane(withoutInsets().gridGap("10", "0"));
  private final MigPane stackContainer = new MigPane(new LC().insets("0", "5", "0", "5").fill());
  private final FxStack stack = new FxStack(stackContainer);
  private final AcceleratorMap acceleratorMap;

  public TopBarHeroView(SubViewRegistry viewFactory, Collection<Stylesheet> stylesheets, RasterLayoutMap layoutMap,
                        AcceleratorMap acceleratorMap) {
    this.subViewFactory = viewFactory;
    this.layoutMap = layoutMap;
    this.acceleratorMap = acceleratorMap;
    stylesheets.forEach(sheet -> sheet.applyToParent(content));
    new Stylesheet("skin/character/hero-view.css").applyToParent(content);
    navigationBar.getStyleClass().add("hero-link-bar");
    navigationBar.setLeft(sectionBar);
    navigationBar.setRight(interactionBar);
    content.add(navigationBar, new CC().growX());
    content.add(stackContainer, new CC().grow().push());
  }

  @Override
  public SectionView addSection(String title) {
    RasterSectionView rasterSectionView = new RasterSectionView(subViewFactory, layoutMap.get(title), () -> {
      Node navigationLabel = createNavigationLabel(title);
      sectionBar.getChildren().add(navigationLabel);
    });
    stack.add(new SimpleIdentifier(title), rasterSectionView.getNode());
    return rasterSectionView;
  }

  @Override
  public InteractionView getInteractionView() {
    return new InteractionView() {
      @Override
      public Tool addTool() {
        FxBaseTool tool = new FxBaseTool(new Hyperlink(), new ImageView());
        tool.getNode().getStyleClass().add("hero-link-button");
        interactionBar.getChildren().add(tool.getNode());
        tool.registerHotkeyIn(acceleratorMap);
        return tool;
      }

      @Override
      public ToggleTool addToggleTool() {
        HyperlinkToggleTool tool = new HyperlinkToggleTool();
        tool.getNode().getStyleClass().add("hero-link-button");
        interactionBar.getChildren().add(tool.getNode());
        tool.registerHotkeyIn(acceleratorMap);
        return tool;
      }

      @Override
      public MenuTool addMenuTool() {
        SplitMenuButton menuButton = new SplitMenuButton();
        FxMenuButtonTool tool = new FxMenuButtonTool(menuButton, new ImageView());
        tool.getNode().getStyleClass().add("hero-link-button");
        interactionBar.getChildren().add(tool.getNode());
        tool.registerHotkeyIn(acceleratorMap);
        return tool;
      }
    };
  }

  @Override
  public Node getNode() {
    return content;
  }

  private Node createNavigationLabel(String text) {
    ButtonBase button = new Hyperlink(text);
    button.getStyleClass().add("hero-link-button");
    button.setOnAction(actionEvent -> stack.show(new SimpleIdentifier(text)));
    return button;
  }
}