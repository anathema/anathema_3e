package net.sf.anathema.hero.magic.advance.creation;

import net.sf.anathema.hero.magic.advance.costs.CostAnalyzer;
import net.sf.anathema.hero.magic.advance.costs.MagicCosts;
import net.sf.anathema.hero.magic.advance.costs.MagicPointsStrategy;
import net.sf.anathema.hero.magic.template.advance.MagicPointsTemplate;
import net.sf.anathema.magic.data.Magic;

import java.util.HashMap;
import java.util.Map;

public class MagicCreationData implements MagicCosts {

  private Map<Boolean, MagicPointsStrategy> strategyByFavored = new HashMap<>();
  private MagicPointsTemplate template;

  public MagicCreationData(MagicPointsTemplate template) {
    this.template = template;
    strategyByFavored.put(true, new MagicPointsStrategy(template.favoredCreationPoints));
    strategyByFavored.put(false, new MagicPointsStrategy(template.generalCreationPoints));
  }

  @Override
  public int getMagicCosts(Magic magic, CostAnalyzer analyzer) {
    boolean favored = analyzer.isMagicFavored(magic);
    return strategyByFavored.get(favored).getMagicCosts(magic);
  }

  public int getFavoredMagicPicks() {
    return template.favoredCreationPoints.freePicks;
  }

  public int getGeneralMagicPicks() {
    return template.generalCreationPoints.freePicks;
  }
}
