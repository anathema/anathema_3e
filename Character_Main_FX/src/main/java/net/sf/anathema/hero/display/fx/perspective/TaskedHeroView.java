package net.sf.anathema.hero.display.fx.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.SubViewRegistry;
import net.sf.anathema.hero.display.fx.CharacterViewSection;
import net.sf.anathema.hero.individual.view.HeroView;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.Stylesheet;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;

public class TaskedHeroView implements HeroView, NodeHolder {

  private final TaskedCharacterPane characterPane = new TaskedCharacterPane();
  private MigPane content;
  private final SubViewRegistry subViewFactory;
  private final Stylesheet[] stylesheets;

  public TaskedHeroView(SubViewRegistry viewFactory, Stylesheet... stylesheets) {
    this.subViewFactory = viewFactory;
    this.stylesheets = stylesheets;
  }

  @Override
  public SectionView addSection(String title) {
    return new CharacterViewSection(characterPane, title, subViewFactory);
  }

  @Override
  public Node getNode() {
    if (content == null) {
      content = new MigPane(fillWithoutInsets(), new AC().index(0).shrink().shrinkPrio(200));
      for (Stylesheet stylesheet : stylesheets) {
        stylesheet.applyToParent(content);
      }
      content.add(characterPane.getNode(), new CC().grow().push());
    }
    return content;
  }
}