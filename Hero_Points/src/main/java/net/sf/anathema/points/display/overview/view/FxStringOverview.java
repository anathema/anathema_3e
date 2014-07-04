package net.sf.anathema.points.display.overview.view;

import javafx.scene.control.Label;
import net.miginfocom.layout.CC;
import net.sf.anathema.library.presenter.FontStyle;
import net.sf.anathema.library.presenter.RGBColor;
import net.sf.anathema.library.view.StyledValueView;
import org.tbee.javafx.scene.layout.MigPane;

public class FxStringOverview implements StyledValueView<String> {
  private final Label titleLabel = new Label();
  private final Label valueLabel = new Label();
  private final FontStyler styler = new FontStyler(titleLabel, valueLabel);

  public FxStringOverview(String labelText) {
    titleLabel.setText(labelText);
  }

  public void addTo(MigPane panel) {
    panel.add(titleLabel);
    panel.add(valueLabel, new CC().alignX("right").span());
  }

  @Override
  public void setValue(final String value) {
    valueLabel.setText(value);
  }

  @Override
  public void setTextColor(RGBColor color) {
    styler.setColor(color);
  }

  @Override
  public void setFontStyle(FontStyle style) {
    styler.setStyle(style);
  }
}
