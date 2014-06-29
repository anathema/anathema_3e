package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.GlobalChangeAdapter;

public abstract class AbstractLimitBreakModel implements LimitBreakModel {

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
  }

  public void addChangeListener(ChangeListener listener) {
    GlobalChangeAdapter<String> adapter = new GlobalChangeAdapter<>(listener);
    getLimitBreak().getName().addTextChangedListener(adapter);
    getLimitBreak().getLimitTrait().addCurrentValueListener(adapter);
  }
}