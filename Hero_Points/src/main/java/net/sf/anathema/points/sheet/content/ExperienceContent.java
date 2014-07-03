package net.sf.anathema.points.sheet.content;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.points.model.ExperiencePointManagementImpl;
import net.sf.anathema.points.model.PointModelFetcher;

import java.text.MessageFormat;

public class ExperienceContent extends AbstractSubBoxContent {

  private Hero hero;

  public ExperienceContent(Resources resources, Hero hero) {
    super(resources);
    this.hero = hero;
  }

  public String getExperienceText() {
    int totalPoints = PointModelFetcher.fetch(hero).getExperiencePoints().getTotalExperiencePoints();
    int spentPoints = new ExperiencePointManagementImpl(hero).getTotalCosts();
    String experienceMessage = getResources().getString("Sheet.Experience.MessageFormat");
    return MessageFormat.format(experienceMessage, totalPoints, spentPoints, totalPoints - spentPoints);
  }

  @Override
  public String getHeaderKey() {
    return "Experience";
  }

  @Override
  public boolean hasContent() {
    return true;
  }
}
