package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.data.cost.Cost;
import net.sf.anathema.charm.data.cost.CostImpl;
import net.sf.anathema.charm.data.cost.CostList;
import net.sf.anathema.charm.data.cost.CostListImpl;
import net.sf.anathema.charm.data.cost.HealthCost;
import net.sf.anathema.charm.data.cost.HealthCostImpl;
import net.sf.anathema.charm.data.cost.HealthCostType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static net.sf.anathema.charm.data.cost.CostImpl.NULL_COST;
import static net.sf.anathema.charm.data.cost.HealthCostImpl.NULL_HEALTH_COST;
import static net.sf.anathema.charm.data.cost.HealthCostType.Aggravated;
import static net.sf.anathema.charm.data.cost.HealthCostType.Bashing;
import static net.sf.anathema.charm.data.cost.HealthCostType.Lethal;

public class CostParser {

  // todo sandra parse permanent and text (e.g. (optional))
  public static final String NO_COST = "0";

  public CostList parse(String costString) {
    Cost essence = parseCost(costString, "m");
    Cost willpower = parseCost(costString, "wp");
    HealthCost health = parseHealthCost(costString);
    Cost xp = parseCost(costString, "xp", true);
    return new CostListImpl(essence, willpower, health, xp);
  }

  public Cost parseCost(String costString, String unit) {
    return parseCost(costString, unit, false);
  }

  private Cost parseCost(String costString, String unit, boolean permanent) {
    for (String costPart : costString.split(",")) {
      String pattern = "(\\d+)" + unit;
      Matcher matcher = Pattern.compile(pattern).matcher(costPart);
      if (matcher.matches()) {
        String costTest = matcher.group(1);
        return new CostImpl(costTest, null, permanent);
      }
    }
    return NULL_COST;
  }

  private String parseCostText(String costString, String unit) {
    for (String costPart : costString.split(",")) {
      String pattern = "(\\d+)" + unit;
      Matcher matcher = Pattern.compile(pattern).matcher(costPart);
      if (matcher.matches()) {
        return matcher.group(1);
      }
    }
    return NO_COST;
  }

  public HealthCost parseHealthCost(String costString) {
    HealthCost bashing = parseHealth(costString, "bhl", Bashing);
    if (bashing != null) {
      return bashing;
    }
    HealthCost lethal = parseHealth(costString, "lhl", Lethal);
    if (lethal != null) {
      return lethal;
    }
    HealthCost aggravated = parseHealth(costString, "ahl", Aggravated);
    if (aggravated != null) {
      return aggravated;
    }
    return NULL_HEALTH_COST;
  }

  private HealthCostImpl parseHealth(String costString, String unit, HealthCostType type) {
    String cost = parseCostText(costString, unit);
    if (!cost.equals(NO_COST)) {
      return new HealthCostImpl(parseInt(cost), null, false, type);
    }
    return null;
  }
}
