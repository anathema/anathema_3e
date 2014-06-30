package net.sf.anathema.hero.points.display.overview.view;

public interface OverviewContainer {

  CategorizedOverview addCreationOverviewView();

  CategorizedOverview addExperienceOverviewView();

  void toggleOverviewView(boolean experienced);
}