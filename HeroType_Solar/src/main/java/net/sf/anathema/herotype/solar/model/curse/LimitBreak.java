package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface LimitBreak {

  TraitType getRoot();

  void setRoot(TraitType root);

  ITextualDescription getName();

  Trait getLimitTrait();

  void addRootChangeListener(ChangeListener listener);
}