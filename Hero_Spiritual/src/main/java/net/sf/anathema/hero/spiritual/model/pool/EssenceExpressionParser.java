package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.hero.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.hero.spiritual.template.PoolPartTemplate;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class EssenceExpressionParser {

  private int base = 0;
  private final ArrayList<PoolPartTemplate> partTemplates = new ArrayList<>();

  public EssenceExpressionParser(String expression) {
    if (expression.isEmpty()) {
      return;
    }
    String[] split = expression.split("\\+");
    for (String summand : split) {
      parseSummand(summand);
    }
  }

  public int getBase() {
    return base;
  }

  public List<PoolPartTemplate> getPartTemplates() {
    return partTemplates;
  }

  private void parseSummand(String expression) {
    try {
      this.base = valueOf(expression);
    } catch (Exception e) {
      parsePartTemplate(expression);
    }
  }

  private void parsePartTemplate(String expression) {
    PoolPartTemplate template = new PoolPartTemplate();
    template.traitType = findTrait(expression);
    template.multiplier = findMultiplier(expression);
    partTemplates.add(template);
  }

  private TraitType findTrait(String expression) {
    String[] split = expression.split("\\*");
    TraitTypeFinder finder = new TraitTypeFinder();
    TraitType type = null;
    for (String part : split) {
      TraitType candidate = finder.getTrait(part);
      if (candidate != null) {
        type = candidate;
      }
    }
    return type;
  }

  private int findMultiplier(String expression) {
    String[] split = expression.split("\\*");
    if (split.length == 1) {
      return 1;
    }
    return valueOf(split[1]);
  }
}
