package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;

public interface SpellViewProperties {

  String getCircleLabel();

  AgnosticUIConfiguration<Identifier> getCircleSelectionRenderer();
}