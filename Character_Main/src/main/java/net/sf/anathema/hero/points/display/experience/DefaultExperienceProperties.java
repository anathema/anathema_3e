package net.sf.anathema.hero.points.display.experience;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.points.display.experience.ExperienceViewProperties;

public class DefaultExperienceProperties implements ExperienceViewProperties {

  private Resources resources;

  public DefaultExperienceProperties(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getTotalString() {
    return resources.getString("CardView.Experience.Total");
  }

  @Override
  public String getPointHeader() {
    return resources.getString("CardView.Experience.ExperiencePoints");
  }

  @Override
  public String getDescriptionHeader() {
    return resources.getString("CardView.Experience.Description");
  }
}