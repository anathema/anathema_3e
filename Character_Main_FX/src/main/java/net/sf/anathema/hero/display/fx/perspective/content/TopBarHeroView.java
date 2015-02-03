package net.sf.anathema.hero.display.fx.perspective.content;

import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Hyperlink;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.hero.application.SubViewRegistry;
import net.sf.anathema.hero.display.fx.perspective.content.layout.RasterLayoutMap;
import net.sf.anathema.hero.individual.view.HeroView;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.Stylesheet;
import net.sf.anathema.library.fx.view.FxStack;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class TopBarHeroView implements HeroView, NodeHolder {

  private MigPane content = new MigPane(withoutInsets().wrapAfter(1), new AC().index(0).shrink().shrinkPrio(200));
  private final SubViewRegistry subViewFactory;
  private final RasterLayoutMap layoutMap;
  private final MigPane navigationBar = new MigPane(withoutInsets().gridGap("10", "0"));
  private final MigPane stackContainer = new MigPane(new LC().insets("0", "5", "0", "5").fill());
  private final FxStack stack = new FxStack(stackContainer);

  public TopBarHeroView(SubViewRegistry viewFactory, Collection<Stylesheet> stylesheets, RasterLayoutMap layoutMap) {
    this.subViewFactory = viewFactory;
    this.layoutMap = layoutMap;
    stylesheets.forEach(sheet -> sheet.applyToParent(content));
    new Stylesheet("skin/character/hero-view.css").applyToParent(content);
    navigationBar.getStyleClass().add("hero-link-bar");
    content.add(navigationBar, new CC().growX());
    content.add(stackContainer, new CC().grow().push());
  }

  @Override
  public SectionView addSection(String title) {
    RasterSectionView rasterSectionView = new RasterSectionView(subViewFactory, () -> {
      Node navigationLabel = createNavigationLabel(title);
      navigationBar.getChildren().add(navigationLabel);
    }, layoutMap.get(title));
    stack.add(new SimpleIdentifier(title), rasterSectionView.getNode());
    return rasterSectionView;
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