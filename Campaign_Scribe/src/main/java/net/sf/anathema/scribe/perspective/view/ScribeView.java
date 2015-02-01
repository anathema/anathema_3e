package net.sf.anathema.scribe.perspective.view;

import net.sf.anathema.library.interaction.AcceleratorMap;
import net.sf.anathema.platform.fx.utility.UtilityPane;
import net.sf.anathema.scribe.editor.view.FxScrollView;

public class ScribeView {
  public final UtilityPane utilityPane = new UtilityPane("skin/scribe/scribe.css");
  public final FxScrollView scrollView = new FxScrollView();
  public final ScribeNavigation scribeNavigation;

  public ScribeView(AcceleratorMap acceleratorMap) {
    scribeNavigation = new ScribeNavigation(acceleratorMap);
    utilityPane.addStyleSheetClass("scribe-utility");
    utilityPane.setNavigationComponent(scribeNavigation.getNode());
    utilityPane.setContentComponent(scrollView.getNode());
  }
}
