package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.display.coloring.CharmDye;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.display.view.FunctionalNodeProperties;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnListener;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.display.TreeView;

import static net.sf.anathema.hero.charms.display.view.NodeIds.toCharmName;

public class LearnInteractionPresenter implements CharmInteractionPresenter {

  private CharmDisplayModel model;
  private FunctionalNodeProperties viewProperties;
  private CharmDye dye;
  private TreeView treeView;

  public LearnInteractionPresenter(CharmDisplayModel model, FunctionalNodeProperties viewProperties, CharmDye dye) {
    this.model = model;
    this.viewProperties = viewProperties;
    this.dye = dye;
  }

  @Override
  public void initPresentation() {
    CharmsModel charms = model.getCharmModel();
    treeView.addNodeInteractionListener(new NodeInteractionListener() {
      @Override
      public void nodeSelected(String nodeId) {
        if (viewProperties.isRequirementNode(nodeId)) {
          return;
        }
        model.toggleLearned(toCharmName(nodeId));
      }

      @Override
      public void nodeDetailsDemanded(String nodeId) {
        // nothing to do
      }
    });
    initCharmLearnListening(charms);
    charms.addLearnableListener(dye::setCharmVisuals);
  }

  private void initCharmLearnListening(CharmsModel charmsModel) {
    ICharmLearnListener charmLearnListener = new CharmLearnVisualizer(dye);
    charmsModel.getLearningModel().addCharmLearnListener(charmLearnListener);
  }

  @Override
  public void operateOn(TreeView treeView) {
    this.treeView = treeView;
  }
}
