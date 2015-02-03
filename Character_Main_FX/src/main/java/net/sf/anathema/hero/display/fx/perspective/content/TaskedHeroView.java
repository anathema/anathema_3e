package net.sf.anathema.hero.display.fx.perspective.content;

import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.SubViewMap;
import net.sf.anathema.hero.individual.view.HeroView;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.Stylesheet;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;

public class TaskedHeroView implements HeroView, NodeHolder {

  private final TaskedHeroNavigation navigation = new TaskedHeroNavigation();
  private final MigPane content = new MigPane(fillWithoutInsets().wrapAfter(1), new AC().index(0).shrink().shrinkPrio(200));;
  private final SubViewMap subViewMap;

  public TaskedHeroView(SubViewMap viewFactory, Collection<Stylesheet> stylesheets) {
    this.subViewMap = viewFactory;
    stylesheets.forEach(sheet -> sheet.applyToParent(content));
    new Stylesheet("skin/character/hero-view.css").applyToParent(content);
    content.add(navigation.getNode(), new CC().grow().push());
  }

  @Override
  public SectionView addSection(String title) {
    return new HeroViewSection(navigation, title, subViewMap);
  }

  @Override
  public Node getNode() {
    return content;
  }
}