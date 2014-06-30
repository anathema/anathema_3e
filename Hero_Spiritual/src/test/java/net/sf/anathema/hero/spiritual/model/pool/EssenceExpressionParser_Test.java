package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.hero.spiritual.template.PoolPartTemplate;
import org.junit.Test;

import java.util.List;

import static net.sf.anathema.hero.traits.model.types.AttributeType.Strength;
import static net.sf.anathema.hero.traits.model.types.OtherTraitType.Essence;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class EssenceExpressionParser_Test {

  @Test
  public void returns0asBaseForEmptyString() throws Exception {
    assertThat(getBase(""), is(0));
  }

  @Test
  public void findsNoTemplatesInEmptyString() throws Exception {
    assertThat(getTemplates(""), hasSize(0));
  }

  @Test
  public void returnsNumberAsBaseValue() throws Exception {
    assertThat(getBase("26"), is(26));
  }

  @Test
  public void interpretsTraitTypeAndReadsMissingMultiplierAs1() throws Exception {
    PoolPartTemplate sample = new PoolPartTemplate();
    sample.traitType = Essence;
    sample.multiplier = 1;
    assertThat(getTemplates("Essence"), hasItem(sample));
  }

  @Test
  public void interpretsAllTraitTypes() throws Exception {
    PoolPartTemplate sample = new PoolPartTemplate();
    sample.traitType = Strength;
    sample.multiplier = 1;
    assertThat(getTemplates("Strength"), hasItem(sample));
  }

  @Test
  public void interpretsMultipliers() throws Exception {
    PoolPartTemplate sample = new PoolPartTemplate();
    sample.traitType = Essence;
    sample.multiplier = 2;
    assertThat(getTemplates("Essence*2"), hasItem(sample));
  }

  @Test
  public void findsBaseInSum() throws Exception {
    assertThat(getBase("Essence+10"), is(10));
  }

  private int getBase(String expression) {
    return new EssenceExpressionParser(expression).getBase();
  }

  private List<PoolPartTemplate> getTemplates(String expression) {
    return new EssenceExpressionParser(expression).getPartTemplates();
  }
}