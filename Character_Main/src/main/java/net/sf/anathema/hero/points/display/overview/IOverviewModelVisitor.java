package net.sf.anathema.hero.points.display.overview;

public interface IOverviewModelVisitor {

  void visitStringValueModel(IValueModel<String> visitedModel);

  void visitIntegerValueModel(IValueModel<Integer> model);

  void visitAllotmentModel(SpendingModel visitedModel);
}