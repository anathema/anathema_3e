package net.sf.anathema.points.persistence;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.points.model.PointsModel;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;

@SuppressWarnings("UnusedDeclaration")
public class PointsPersister extends AbstractModelJsonPersister<PointsPto, PointsModel> {

  public PointsPersister() {
    super("points", PointsPto.class);
  }

  @Override
  public Identifier getModelId() {
    return PointsModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, PointsModel model, PointsPto pto) {
    for (ExperiencePointsEntryPto pointsPto : pto.experiencePoints) {
      ExperiencePointEntry entry = model.getExperiencePoints().addEntry();
      loadPointsEntry(pointsPto, entry);
    }
  }

  private void loadPointsEntry(ExperiencePointsEntryPto pointsPto, ExperiencePointEntry entry) {
    entry.setExperiencePoints(pointsPto.points);
    entry.getTextualDescription().setText(pointsPto.description);
  }

  @Override
  protected PointsPto saveModelToPto(PointsModel model) {
    PointsPto pto = new PointsPto();
    for (ExperiencePointEntry entry : model.getExperiencePoints().getAllEntries()) {
      ExperiencePointsEntryPto pointsPto = createExperiencePointsPto(entry);
      pto.experiencePoints.add(pointsPto);
    }
    return pto;
  }

  private ExperiencePointsEntryPto createExperiencePointsPto(ExperiencePointEntry entry) {
    ExperiencePointsEntryPto pointsPto = new ExperiencePointsEntryPto();
    pointsPto.description = entry.getTextualDescription().getText();
    pointsPto.points = entry.getExperiencePoints();
    return pointsPto;
  }
}
