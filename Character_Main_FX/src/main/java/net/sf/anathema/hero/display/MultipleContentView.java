package net.sf.anathema.hero.display;

import net.sf.anathema.library.fx.NodeHolder;

public interface MultipleContentView {

  void addView(NodeHolder view, ContentProperties tabProperties);

  void finishInitialization();
}