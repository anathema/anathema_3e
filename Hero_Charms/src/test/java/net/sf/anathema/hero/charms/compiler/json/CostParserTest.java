package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.data.cost.Cost;
import net.sf.anathema.charm.data.cost.CostImpl;
import net.sf.anathema.charm.data.cost.CostList;
import net.sf.anathema.charm.data.cost.HealthCost;
import net.sf.anathema.charm.data.cost.HealthCostImpl;
import org.junit.Test;

import static net.sf.anathema.charm.data.cost.HealthCostType.Aggravated;
import static net.sf.anathema.charm.data.cost.HealthCostType.Bashing;
import static net.sf.anathema.charm.data.cost.HealthCostType.Lethal;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CostParserTest {

  private CostParser parser = new CostParser();

  @Test
  public void doesNotParseEmptyString() {
    Cost cost = parser.parseCost("", "m");
    assertThat(cost, is(CostImpl.NULL_COST));
  }

  @Test
  public void parsesUnit() {
    Cost cost = parser.parseCost("1unit", "unit");
    assertThat(cost, is(new CostImpl("1", null, false)));
  }

  @Test
  public void parsesOtherValueForUnit() {
    Cost cost = parser.parseCost("12unit", "unit");
    assertThat(cost, is(new CostImpl("12", null, false)));
  }

  @Test
  public void parsesUnitAsSecondCostEntry() {
    Cost cost = parser.parseCost("any,5unit", "unit");
    assertThat(cost, is(new CostImpl("5", null, false)));
  }

  @Test
  public void parsesOtherUnit() {
    Cost cost = parser.parseCost("2otherUnit", "otherUnit");
    assertThat(cost, is(new CostImpl("2", null, false)));
  }

  @Test
  public void parsesMotes() {
    CostList costList = parser.parse("3m,2wp,1xp");
    assertThat(costList.getEssenceCost(), is(new CostImpl("3", null, false)));
  }

  @Test
  public void parsesWillpower() {
    CostList costList = parser.parse("3m,2wp,1xp");
    assertThat(costList.getWillpowerCost(), is(new CostImpl("2", null, false)));
  }

  @Test
  public void parsesXp() {
    CostList costList = parser.parse("3m,2wp,1xp");
    assertThat(costList.getXPCost(), is(new CostImpl("1", null, true)));
  }

  @Test
  public void parsesBashingHealthCost() {
    HealthCost cost = parser.parseHealthCost("1bhl");
    assertThat(cost, is(new HealthCostImpl(1, null, false, Bashing)));
  }

  @Test
  public void parsesLethalHealthCost() {
    HealthCost cost = parser.parseHealthCost("2lhl");
    assertThat(cost, is(new HealthCostImpl(2, null, false, Lethal)));
  }

  @Test
  public void parsesAggravatedHealthCost() {
    HealthCost cost = parser.parseHealthCost("3ahl");
    assertThat(cost, is(new HealthCostImpl(3, null, false, Aggravated)));
  }
}
