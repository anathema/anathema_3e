package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.library.view.BooleanView;
import net.sf.anathema.platform.tree.display.SpecialCharmContainer;

import java.util.ArrayList;
import java.util.List;

public class BooleanSelectionSpecialNodeView implements ToggleButtonSpecialNodeView {
  private final List<ProxyBooleanView> views = new ArrayList<>();
  private String id;

  @Override
  public String getNodeId() {
    return id;
  }

  @Override
  public void setNodeId(String nodeId) {
    this.id = nodeId;
  }

  @Override
  public void showIn(SpecialCharmContainer container) {
    for (ProxyBooleanView view : views) {
      BooleanView actualView = container.add(BooleanView.class, view.getLabel());
      view.setActualView(actualView);
    }
  }

  @Override
  public BooleanView addSubeffect(String label) {
    ProxyBooleanView proxyBooleanValueView = new ProxyBooleanView(label);
    views.add(proxyBooleanValueView);
    return proxyBooleanValueView;
  }
}