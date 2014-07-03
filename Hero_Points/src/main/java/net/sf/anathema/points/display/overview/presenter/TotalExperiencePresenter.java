package net.sf.anathema.points.display.overview.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.messaging.MessageToken;
import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.hero.framework.item.HeroNameFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.points.model.ExperiencePointManagement;
import net.sf.anathema.points.model.PointModelFetcher;

import static java.text.MessageFormat.format;
import static net.sf.anathema.lib.message.MessageDuration.Permanent;
import static net.sf.anathema.lib.message.MessageType.NORMAL;
import static net.sf.anathema.lib.message.MessageType.WARNING;

public class TotalExperiencePresenter implements IOverviewSubPresenter {
  private final Hero hero;
  private final Resources resources;
  private final ExperiencePointManagement management;
  private final MessageToken token;

  public TotalExperiencePresenter(Hero hero, Resources resources, Messaging messaging, ExperiencePointManagement management) {
    this.hero = hero;
    this.resources = resources;
    this.token = messaging.obtainInitialToken();
    this.management = management;
  }

  @Override
  public void update() {
    String name = new HeroNameFetcher().getName(hero);
    int spending = management.getTotalCosts();
    int allotment = PointModelFetcher.fetch(hero).getExperiencePoints().getTotalExperiencePoints();
    String pattern;
    MessageType type;
    if (spending <= allotment) {
      pattern = resources.getString("Overview.Creation.ExperiencePoints.Spent");
      type = NORMAL;
    } else {
      pattern = resources.getString("Overview.Creation.ExperiencePoints.Overspent");
      type = WARNING;
    }
    token.replaceMessage(new Message(format(pattern, name, spending, allotment), type, Permanent));
  }
}
