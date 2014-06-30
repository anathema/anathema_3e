package net.sf.anathema.hero.points.advance.experience;

import net.sf.anathema.hero.points.model.overview.IValueModel;

import java.util.List;

public interface ExperiencePointManagement {

  int getTotalCosts();

  List<IValueModel<Integer>> getAllModels();
}