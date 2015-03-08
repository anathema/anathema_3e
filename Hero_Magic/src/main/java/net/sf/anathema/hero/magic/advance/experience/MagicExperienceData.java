package net.sf.anathema.hero.magic.advance.experience;

import net.sf.anathema.hero.magic.advance.costs.CostAnalyzer;
import net.sf.anathema.hero.magic.advance.costs.MagicCosts;
import net.sf.anathema.hero.magic.advance.costs.MagicPointsStrategy;
import net.sf.anathema.hero.magic.template.advance.MagicPointsTemplate;
import net.sf.anathema.magic.data.Magic;

import java.util.HashMap;
import java.util.Map;

public class MagicExperienceData implements MagicCosts {

  private Map<Boolean, MagicPointsStrategy> strategyByFavored = new HashMap<>();

  public MagicExperienceData(MagicPointsTemplate template) {
    strategyByFavored.put(true, new MagicPointsStrategy(template.favoredExperiencePoints));
    strategyByFavored.put(false, new MagicPointsStrategy(template.generalExperiencePoints));
  }

  @Override
  public int getMagicCosts(Magic magic, CostAnalyzer analyzer) {
    boolean favored = analyzer.isMagicFavored(magic);
    return strategyByFavored.get(favored).getMagicCosts(magic);
  }
}
