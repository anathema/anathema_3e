package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitLimitation;
import net.sf.anathema.points.model.PointModelFetcher;
import net.sf.anathema.points.model.PointsModel;
import net.sf.anathema.points.model.xp.ExperiencePoints;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExperienceBasedLimitation implements TraitLimitation {

  //TODO (Urs): Introduce offset to hand in via template instead of going through the character type 
  private Map<Integer, Integer> defaultLimitByXp = new LinkedHashMap<Integer,Integer>() {{
    put(0, 1);
  }};


  private Map<Integer, Integer> rookieLimitByXp = new LinkedHashMap<Integer,Integer>() {{
    put(0, 1);
    put(50, 2);
    put(120, 3);
    put(200, 4);
    put(300, 5);
  }};

  private Map<Integer, Integer> bigDamnLimitByXp = new LinkedHashMap<Integer,Integer>() {{
    put(0, 2);
    put(70, 3);
    put(150, 4);
    put(250, 5);
  }};

  @Override
  public int getAbsoluteLimit(Hero hero) {
    return getCurrentMaximum(hero, true);
  }

  @Override
  public int getCurrentMaximum(Hero hero, boolean modified) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    ExperiencePoints experiencePoints = pointsModel.getExperiencePoints();
    int totalExperiencePoints = experiencePoints.getTotalExperiencePoints();
    Map<Integer, Integer> maximumByXp = chooseTableOfMaxima(hero);
    int maximum = maximumByXp.get(0);
    for (Integer xpLimit : maximumByXp.keySet()) {
      if (totalExperiencePoints < xpLimit) {
        break;
      } else {
        maximum = maximumByXp.get(xpLimit);
      }
    }
    return maximum;
  }

  private Map<Integer, Integer> chooseTableOfMaxima(Hero hero) {
    if (hero.getSplat().getTemplateType().getSubType().getId().equals("RookieLawgiver")) {
      return rookieLimitByXp;
    }
    if (hero.getSplat().getTemplateType().getSubType().getId().equals("BigDamnHero")) {
      return bigDamnLimitByXp;
    }
    return   defaultLimitByXp;
  }
}
