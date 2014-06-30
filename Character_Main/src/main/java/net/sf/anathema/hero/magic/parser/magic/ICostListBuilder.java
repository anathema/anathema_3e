package net.sf.anathema.hero.magic.parser.magic;

import net.sf.anathema.hero.magic.basic.cost.ICostList;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public interface ICostListBuilder {

  ICostList buildCostList(Element costListElement) throws PersistenceException;
}