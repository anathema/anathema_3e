package net.sf.anathema.hero.display.fx.perspective.content;

import net.sf.anathema.library.fx.NodeHolder;

public interface MultipleContentView {

  void addView(NodeHolder view, ContentProperties tabProperties);

  void finishInitialization();
}