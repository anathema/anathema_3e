package net.sf.anathema;

import net.sf.anathema.hero.points.model.PointModelFetcher;
import net.sf.anathema.hero.points.model.PointsModel;
import net.sf.anathema.hero.points.model.overview.IOverviewModel;
import net.sf.anathema.hero.points.model.overview.IValueModel;

public class BonusModelFetcher {

  private CharacterHolder character;

  public BonusModelFetcher(CharacterHolder character) {
    this.character = character;
  }

  public IValueModel<Integer> findBonusModel(String category, String id) {
    PointsModel pointsModel = PointModelFetcher.fetch(character.getHero());
    for (IOverviewModel model : pointsModel.getBonusOverviewModels()) {
      if (model.getId().equals(id) && model.getCategoryId().equals(category)) {
        return (IValueModel<Integer>) model;
      }
    }
    throw new IllegalArgumentException();
  }
}