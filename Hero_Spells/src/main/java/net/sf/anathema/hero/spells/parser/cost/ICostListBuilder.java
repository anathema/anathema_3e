package net.sf.anathema.hero.spells.parser.cost;

import net.sf.anathema.charm.data.cost.CostList;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public interface ICostListBuilder {

  CostList buildCostList(Element costListElement) throws PersistenceException;
}