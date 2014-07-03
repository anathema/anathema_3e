package net.sf.anathema.hero.spiritual.sheet.essence.content;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.spiritual.model.pool.EssencePoolModelFetcher;
import net.sf.anathema.hero.spiritual.model.traits.SpiritualTraitModelFetcher;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

public class SimpleEssenceContent extends AbstractSubBoxContent {

  private Hero hero;

  public SimpleEssenceContent(Resources resources, Hero hero) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Essence";
  }

  @Override
  public boolean hasContent() {
    return true;
  }

  public int getEssenceValue() {
    return SpiritualTraitModelFetcher.fetch(hero).getTrait(OtherTraitType.Essence).getCurrentValue();
  }

  public int getEssenceMax() {
    return SpiritualTraitModelFetcher.fetch(hero).getEssenceLimitation().getAbsoluteLimit(hero);
  }

  public int getNumberOfPoolLines() {
    return (hasPersonalPool() ? 1 : 0) + (hasPeripheralPool() ? 1 : 0);
  }

  public boolean hasPeripheralPool() {
    return getPeripheralPool() != null;
  }

  public boolean hasPersonalPool() {
    return getPersonalPool() != null;
  }

  public String getPeripheralPool() {
    try {
      return EssencePoolModelFetcher.fetch(hero).getPeripheralPool();
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  public String getPersonalPool() {
    return EssencePoolModelFetcher.fetch(hero).getPersonalPool();
  }

  public String getPersonalPoolLabel() {
    return getResources().getString("Sheet.Essence.PersonalPool");
  }

  public String getPeripheralPoolLabel() {
    return getResources().getString("Sheet.Essence.PeripheralPool");
  }

  public String getAvailableText() {
    return " " + getResources().getString("Sheet.Essence.Available");
  }

  public String getTotalString(String poolValue) {
    return poolValue + " " + getResources().getString("Sheet.Essence.Total") + " / ";
  }
}
