package net.sf.anathema.points.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.points.model.overview.IValueModel;

public class ExperiencePointManagementImpl implements ExperiencePointManagement {

	PointsModel points;
	
  public ExperiencePointManagementImpl(PointsModel points) {
    this.points = points;
  }

  @Override
  public List<IValueModel<Integer>> getAllModels() {
    final List<IValueModel<Integer>> allModels = new ArrayList<>();
    // todo (sandra): Sorting mechanism for the value models
    for (IValueModel<Integer>  model : points.getExperienceOverviewModels()) {
      allModels.add(model);
    }
    return allModels;
  }

  @Override
  public int getTotalCosts() {
    int experienceCosts = 0;
    for (IValueModel<Integer> model : getAllModels()) {
      experienceCosts += model.getValue();
    }
    return experienceCosts;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (IValueModel<Integer> model : getAllModels()) {
      builder.append(model.getCategoryId());
      builder.append(": ");
      builder.append(model.getValue());
    }
    builder.append("Overall: ");
    builder.append(getTotalCosts());
    return builder.toString();
  }
}