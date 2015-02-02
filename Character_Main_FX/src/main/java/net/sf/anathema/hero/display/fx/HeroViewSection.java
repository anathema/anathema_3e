package net.sf.anathema.hero.display.fx;

import net.sf.anathema.hero.application.SubViewRegistry;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.fx.NodeHolder;

public class HeroViewSection implements SectionView {

  private final MultipleContentView view;
  private final SubViewRegistry subViewFactory;

  public HeroViewSection(HeroPane heroPane, String title, SubViewRegistry subViewFactory) {
    this.view = heroPane.addMultipleContentView(title);
    this.subViewFactory = subViewFactory;
  }

  @Override
  public <T> T addView(String title, Class<T> viewClass) {
    T newView = subViewFactory.get(viewClass);
    NodeHolder viewToAdd = (NodeHolder) newView;
    view.addView(viewToAdd, new ContentProperties(title));
    return newView;
  }

  @Override
  public void finishInitialization() {
    view.finishInitialization();
  }
}