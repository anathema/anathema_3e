package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.hero.charms.template.advance.KeywordMagicTemplate;
import net.sf.anathema.hero.charms.template.advance.MagicPointsCategoryTemplate;
import net.sf.anathema.magic.data.Magic;

import java.util.HashMap;
import java.util.Map;

public class MagicPointsStrategy {

  private MagicPointsCategoryTemplate template;
  private final MagicKeywordCosts keywordCosts;

  public MagicPointsStrategy(MagicPointsCategoryTemplate template) {
    this.template = template;
    this.keywordCosts = createKeywordCosts(template);
  }

  private MagicKeywordCosts createKeywordCosts(MagicPointsCategoryTemplate template) {
    Map<String, Integer> keywordFavoredCosts = new HashMap<>();
    for (KeywordMagicTemplate keywordMagic : template.keywordMagic) {
      keywordFavoredCosts.put(keywordMagic.keyword, keywordMagic.costs);
    }
    return new MagicKeywordCosts(keywordFavoredCosts);
  }

  public int getMagicCosts(Magic magic, CostAnalyzer analyzer) {
    if (keywordCosts.hasCostFor(magic.getAttributes())) {
      return keywordCosts.getCostFor(magic.getAttributes());
    }
    return template.costs;
  }
}
