package net.sf.anathema.charm.parser.cost;

import net.sf.anathema.charm.old.cost.Cost;
import net.sf.anathema.charm.old.cost.CostList;
import net.sf.anathema.charm.old.cost.CostListImpl;
import net.sf.anathema.charm.old.cost.HealthCost;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class CostListBuilder implements ICostListBuilder {

  private final CostBuilder costBuilder = new CostBuilder();
  private final HealthCostBuilder healthCostBuilder = new HealthCostBuilder();

  @Override
  public CostList buildCostList(Element costListElement) throws PersistenceException {
    try {
      Cost essenceCost = costBuilder.buildCost(costListElement.element("essence"));
      Cost willpowerCost = costBuilder.buildCost(costListElement.element("willpower"));
      HealthCost healthCost = healthCostBuilder.buildCost(costListElement.element("health"));
      Cost xpCost = costBuilder.buildCost(costListElement.element("xp"));
      return new CostListImpl(essenceCost, willpowerCost, healthCost, xpCost);
    } catch (Exception e) {
      return new CostListImpl(null, null, null, null);
    }
  }
}