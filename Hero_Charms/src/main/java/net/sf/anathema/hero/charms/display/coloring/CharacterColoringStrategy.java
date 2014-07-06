package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.library.presenter.RGBColor;
import net.sf.anathema.platform.tree.display.TreeView;

import static net.sf.anathema.hero.charms.model.learn.prerequisites.IsSatisfied.isSatisfied;

public class CharacterColoringStrategy implements CharmColoring {

  private static final int MAXIMUM_OPACITY = 255;
  private static final int REDUCED_OPACITY = 70;
  private static final RGBColor UNSELECTED_COLOR = RGBColor.White;

  private final RGBColor characterColor;
  private final CharmDisplayModel model;
  private TreeView treeView;

  public CharacterColoringStrategy(RGBColor characterColor, CharmDisplayModel model) {
    this.characterColor = characterColor;
    this.model = model;
  }

  @Override
  public void colorCharm(Charm charm) {
    String id = charm.getName().text;
    RGBColor color = getCharmModel().getLearningModel().isCurrentlyLearned(charm) ? characterColor : UNSELECTED_COLOR;
    int opacity = getCharmModel().isLearnable(charm) ? MAXIMUM_OPACITY : REDUCED_OPACITY;
    treeView.colorNode(id, new RGBColor(color, opacity));
  }

  @Override
  public void colorNonCharmPrerequisite(String nodeId, CharmPrerequisite prerequisite) {
    boolean fulfilled = isSatisfied(prerequisite, getCharmModel());
    RGBColor color = fulfilled ? characterColor.brighter() : UNSELECTED_COLOR;
    treeView.colorNode(nodeId, new RGBColor(color, MAXIMUM_OPACITY));
  }

  @Override
  public void operateOn(TreeView treeView) {
    this.treeView = treeView;
  }

  private CharmsModel getCharmModel() {
    return model.getCharmModel();
  }
}