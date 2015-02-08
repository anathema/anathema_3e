package net.sf.anathema.herotype.solar.sheet.curse;

import com.google.common.base.Strings;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.herotype.solar.model.curse.DescriptiveLimitBreak;
import net.sf.anathema.herotype.solar.model.curse.DescriptiveLimitBreakModel;
import net.sf.anathema.herotype.solar.model.curse.GreatCurseFetcher;
import net.sf.anathema.library.resources.Resources;

public class VirtueFlawContent extends AbstractSubBoxContent {

  private Hero hero;

  protected VirtueFlawContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "GreatCurse.Solar";
  }

  public int getLimitValue() {
    return getVirtueFlawModel().getLimitTrait().getCurrentValue();
  }

  public String getLimitTrigger() {
    return getVirtueFlawModel().getLimitTrigger().getText();
  }

  public boolean isNameDefined() {
    return false;
  }

  public boolean isConditionDefined() {
    return !Strings.isNullOrEmpty(getLimitTrigger());
  }

  private DescriptiveLimitBreak getVirtueFlawModel() {
    return ((DescriptiveLimitBreakModel) GreatCurseFetcher.fetch(hero)).getLimitBreak();
  }
}