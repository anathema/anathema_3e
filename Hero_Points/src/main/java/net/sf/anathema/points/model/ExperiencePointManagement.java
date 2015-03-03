package net.sf.anathema.points.model;

import net.sf.anathema.points.model.overview.IValueModel;

import java.util.List;

public interface ExperiencePointManagement {

  int getTotalCosts();

  List<IValueModel<Integer>> getAllModels();
}