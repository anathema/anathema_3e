package net.sf.anathema.hero.charms.advance.experience;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.hero.charms.advance.costs.CostAnalyzer;
import net.sf.anathema.hero.charms.advance.costs.MagicCosts;
import net.sf.anathema.hero.charms.advance.costs.MagicPointsStrategy;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
import net.sf.anathema.magic.data.Magic;

public class MagicExperienceData implements MagicCosts {

  private Map<Boolean, MagicPointsStrategy> strategyByFavored = new HashMap<>();

  public MagicExperienceData(MagicPointsTemplate template) {
    strategyByFavored.put(true, new MagicPointsStrategy(template.favoredExperiencePoints));
    strategyByFavored.put(false, new MagicPointsStrategy(template.generalExperiencePoints));
  }

  @Override
  public int getMagicCosts(Magic magic, CostAnalyzer analyzer) {
    boolean favored = analyzer.isMagicFavored(magic);
    return strategyByFavored.get(favored).getMagicCosts(magic, analyzer);
  }
}
