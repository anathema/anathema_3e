package net.sf.anathema.points.display.overview.presenter;

import net.sf.anathema.library.legality.LegalityColorProvider;
import net.sf.anathema.library.legality.LegalityFontProvider;
import net.sf.anathema.library.legality.ValueLegalityState;
import net.sf.anathema.library.presenter.FontStyle;
import net.sf.anathema.library.view.StyledView;
import net.sf.anathema.points.model.overview.SpendingModel;

public class FontParameterSetter {

  private final StyledView view;
  private final SpendingModel model;

  public FontParameterSetter(SpendingModel model, StyledView view) {
    this.model = model;
    this.view = view;
  }

  public void setFontParameters() {
    ValueLegalityState fontStyleState = model.getSpentBonusPoints() > 0 ? ValueLegalityState.Increased : ValueLegalityState.Okay;
    FontStyle fontStyle = new LegalityFontProvider().getFontStyle(fontStyleState);
    view.setFontStyle(fontStyle);
    LegalityColorProvider legalityColorProvider = new LegalityColorProvider();
    ValueLegalityState textColorState = null;
    int alotment = model.getAllotment();
    if (model.getValue() < alotment) {
      textColorState = ValueLegalityState.Lowered;
    }
    if (model.getValue() == alotment) {
      textColorState = ValueLegalityState.Okay;
    }
    if (model.getValue() > alotment) {
      textColorState = ValueLegalityState.High;
    }
    view.setTextColor(legalityColorProvider.getTextColor(textColorState));
  }
}
