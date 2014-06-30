package net.sf.anathema.points.display.overview.view;

public interface OverviewContainer {

  CategorizedOverview addCreationOverviewView();

  CategorizedOverview addExperienceOverviewView();

  void toggleOverviewView(boolean experienced);
}