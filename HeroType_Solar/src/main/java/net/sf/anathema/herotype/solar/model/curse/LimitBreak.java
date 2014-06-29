package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface LimitBreak {

  ITextualDescription getName();

  Trait getLimitTrait();
}