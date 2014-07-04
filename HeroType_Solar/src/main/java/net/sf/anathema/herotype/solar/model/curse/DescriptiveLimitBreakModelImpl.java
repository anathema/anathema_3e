package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.UnspecifiedChangeListener;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.event.GlobalChangeAdapter;
import net.sf.anathema.library.identifier.Identifier;

public class DescriptiveLimitBreakModelImpl extends AbstractLimitBreakModel implements DescriptiveLimitBreakModel {

  private DescriptiveLimitBreak virtueFlaw;

  @Override
  public final Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    super.initialize(environment, hero);
    virtueFlaw = new DescriptiveLimitBreakImpl(hero);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    addChangeListener(new UnspecifiedChangeListener(announcer));
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    super.addChangeListener(listener);
    GlobalChangeAdapter<String> changeAdapter = new GlobalChangeAdapter<>(listener);
    virtueFlaw.getDescription().addTextChangedListener(changeAdapter);
    virtueFlaw.getLimitBreak().addTextChangedListener(changeAdapter);
  }

  @Override
  public DescriptiveLimitBreak getLimitBreak() {
    return virtueFlaw;
  }
}