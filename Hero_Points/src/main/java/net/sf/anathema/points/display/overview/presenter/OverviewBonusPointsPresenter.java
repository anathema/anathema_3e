package net.sf.anathema.points.display.overview.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.hero.framework.item.HeroNameFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.points.model.overview.SpendingModel;

import static java.text.MessageFormat.format;
import static net.sf.anathema.lib.message.MessageDuration.Permanent;
import static net.sf.anathema.lib.message.MessageType.NORMAL;
import static net.sf.anathema.lib.message.MessageType.WARNING;

class OverviewBonusPointsPresenter implements IOverviewSubPresenter {
  private Resources resources;
  private SpendingModel model;
  private Messaging messaging;
  private Hero hero;

  public OverviewBonusPointsPresenter(Resources resources, SpendingModel model, Messaging messaging, Hero hero) {
    this.resources = resources;
    this.model = model;
    this.messaging = messaging;
    this.hero = hero;
  }

  @Override
  public void update() {
    String name = new HeroNameFetcher().getName(hero);
    int spending = model.getValue();
    int allotment = model.getAllotment();
    String pattern;
    MessageType type;
    if (spending <= allotment) {
      pattern = resources.getString("Overview.Creation.BonusPoints.Spent");
      type = NORMAL;
    } else {
      pattern = resources.getString("Overview.Creation.BonusPoints.Overspent");
      type = WARNING;
    }
    messaging.addMessage(new Message(format(pattern, name, spending, allotment), type, Permanent));
  }
}