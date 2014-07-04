package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArtifactStatisticsProperties;
import net.sf.anathema.hero.display.fx.configurableview.IIntegerSpinner;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.BooleanView;

public class ArtifactStatisticsPresenter {
  private final IArtifactStatisticsModel artifactModel;
  private final EquipmentStatsView view;
  private final ArtifactStatisticsProperties properties;

  public ArtifactStatisticsPresenter(IArtifactStatisticsModel artifactModel, EquipmentStatsView view,
                                     Resources resources) {
    this.artifactModel = artifactModel;
    this.view = view;
    this.properties = new ArtifactStatisticsProperties(resources);
  }

  public void initPresentation() {
    addSpinner(properties.getAttuneCostLabel(), artifactModel.getAttuneCostModel());

    BooleanView foreignAttuneBox = view.addBooleanSelector(properties.getForeignAttuneLabel());
    BooleanView requireAttuneBox = view.addBooleanSelector(properties.getRequireAttuneLabel());
    
    BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();

    foreignAttuneBox.setSelected(artifactModel.getForeignAttunementModel().getValue());
    requireAttuneBox.setSelected(artifactModel.getRequireAttunementModel().getValue());

    booleanValuePresentation.initPresentation(foreignAttuneBox, artifactModel.getForeignAttunementModel());
    booleanValuePresentation.initPresentation(requireAttuneBox, artifactModel.getRequireAttunementModel());
  }

  private void addSpinner(String label, IIntValueModel model) {
    IIntegerSpinner spinner = view.addIntegerSpinner(label);
    new IntValuePresentation().initPresentation(spinner, model);
  }
}
