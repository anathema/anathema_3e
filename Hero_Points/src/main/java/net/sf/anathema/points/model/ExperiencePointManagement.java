package net.sf.anathema.points.model;

import java.util.List;

import net.sf.anathema.points.model.overview.IValueModel;

public interface ExperiencePointManagement {

  int getTotalCosts();

  List<IValueModel<Integer>> getAllModels();
}