package net.sf.anathema.hero.display.fx;

import net.sf.anathema.library.fx.NodeHolder;

public interface MultipleContentView {

  void addView(NodeHolder view, ContentProperties tabProperties);

  void finishInitialization();
}