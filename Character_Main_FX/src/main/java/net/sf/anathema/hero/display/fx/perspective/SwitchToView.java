package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.library.fx.view.FxStack;
import net.sf.anathema.library.identifier.Identifier;

public class SwitchToView {

  private final Identifier name;
  private final FxStack stack;

  public SwitchToView(Identifier name, FxStack stack) {
    this.name = name;
    this.stack = stack;
  }

  public void execute() {
    stack.show(name);
  }
}