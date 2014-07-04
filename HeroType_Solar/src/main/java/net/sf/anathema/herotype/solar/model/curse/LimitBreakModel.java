package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface LimitBreakModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("GreatCurse");

  LimitBreak getLimitBreak();
}