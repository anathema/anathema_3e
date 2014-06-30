package net.sf.anathema.fx.hero.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.framework.display.CharacterView;
import net.sf.anathema.hero.framework.display.SectionView;
import net.sf.anathema.hero.framework.display.SubViewRegistry;
import net.sf.anathema.hero.display.CharacterViewSection;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.Stylesheet;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class TaskedCharacterView implements CharacterView, NodeHolder {

  private final TaskedCharacterPane characterPane = new TaskedCharacterPane();
  private MigPane content;
  private final SubViewRegistry subViewFactory;
  private final Stylesheet[] stylesheets;

  public TaskedCharacterView(SubViewRegistry viewFactory, Stylesheet... stylesheets) {
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