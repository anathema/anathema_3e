package net.sf.anathema.herotype.solar.display.curse;

import net.sf.anathema.herotype.solar.model.curse.DescriptiveLimitBreak;
import net.sf.anathema.herotype.solar.model.curse.DescriptiveLimitBreakModel;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.text.TextualPresentation;
import net.sf.anathema.library.view.ConfigurableCharacterView;

public class SolarVirtueFlawPresenter extends VirtueFlawPresenter {

  private final ConfigurableCharacterView view;
  private final DescriptiveLimitBreakModel model;

  public SolarVirtueFlawPresenter(Resources resources, ConfigurableCharacterView view, DescriptiveLimitBreakModel model) {
    super(resources, view, model);
    this.view = view;
    this.model = model;
  }

  @Override
  protected void initAdditionalPresentation() {
    DescriptiveLimitBreak virtueFlaw = model.getLimitBreak();
    TextualPresentation presentation = new TextualPresentation();
    ITextView conditionView = view.addAreaView(getResources().getString("VirtueFlaw.LimitTrigger.Name"));
    presentation.initView(conditionView, virtueFlaw.getLimitTrigger());
  }
}