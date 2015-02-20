package net.sf.anathema.equipment.editor.stats.presenter;

import net.sf.anathema.equipment.editor.presenter.EquipmentStatsView;
import net.sf.anathema.equipment.editor.stats.model.IArtifactStatisticsModel;
import net.sf.anathema.equipment.editor.stats.model.IIntValueModel;
import net.sf.anathema.equipment.editor.stats.properties.ArtifactStatisticsProperties;
import net.sf.anathema.library.fx.configurableview.IIntegerSpinner;
import net.sf.anathema.library.resources.Resources;

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
  }

  private void addSpinner(String label, IIntValueModel model) {
    IIntegerSpinner spinner = view.addIntegerSpinner(label);
    new IntValuePresentation().initPresentation(spinner, model);
  }
}
