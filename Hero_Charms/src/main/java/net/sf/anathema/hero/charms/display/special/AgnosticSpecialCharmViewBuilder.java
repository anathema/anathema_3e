package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.display.view.SpecialCharmViewContainer;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.ICharmSpecialLearningVisitor;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.IMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.MultiLearnCharmSpecials;
import net.sf.anathema.hero.charms.model.special.subeffects.IMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.ISubEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharmSpecials;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.tree.display.CategorizedSpecialNodeView;
import net.sf.anathema.platform.tree.display.SpecialNodeView;

public class AgnosticSpecialCharmViewBuilder implements SpecialCharmViewBuilder {

  private SpecialNodeView result;
  private final Resources resources;
  private final CharmsModel configuration;
  private final SpecialCharmViewContainer view;

  public AgnosticSpecialCharmViewBuilder(Resources resources, CharmsModel configuration, SpecialCharmViewContainer view) {
    this.resources = resources;
    this.configuration = configuration;
    this.view = view;
  }

  @Override
  public SpecialNodeView getResult() {
    return result;
  }

  @Override
  public void reset() {
    result = null;
  }

  @Override
  public boolean hasResult() {
    return result != null;
  }

  @Override
  public void buildFor(CharmSpecialLearning charm) {
    charm.accept(new AgnosticSpecialCharmVisitor());
  }

  private class AgnosticSpecialCharmVisitor implements ICharmSpecialLearningVisitor {

    @Override
    public void visitMultiLearnableCharm(IMultiLearnableCharm visitedCharm) {
      CategorizedSpecialNodeView view = createCategorizedView(visitedCharm);
      MultiLearnCharmSpecials model = getModelFromCharm(visitedCharm);
      new MultiLearnableCharmPresenter(resources, view, model).initPresentation();
      result = view;
    }

    @Override
    public void visitSubEffectCharm(ISubEffectCharm visited) {
      createMultipleEffectCharmView(visited);
    }

    @Override
    public void visitMultipleEffectCharm(IMultipleEffectCharm visited) {
      createMultipleEffectCharmView(visited);
    }

    private void createMultipleEffectCharmView(IMultipleEffectCharm visitedCharm) {
      ToggleButtonSpecialNodeView view = createBooleanView(visitedCharm);
      MultipleEffectCharmSpecials model = getModelFromCharm(visitedCharm);
      new MultipleEffectCharmPresenter(resources, view, model).initPresentation();
      result = view;
    }

    @SuppressWarnings("unchecked")
    private <T> T getModelFromCharm(CharmSpecialLearning visitedCharm) {
      Charm charm = configuration.getCharmById(visitedCharm.getCharmName());
      return (T) configuration.getCharmSpecialLearningModel(charm);
    }
  }

  private ToggleButtonSpecialNodeView createBooleanView(IMultipleEffectCharm visitedCharm) {
    ToggleButtonSpecialNodeView specialView = view.createToggleButtonSpecialView();
    specialView.setNodeId(visitedCharm.getCharmName().text);
    return specialView;
  }

  private CategorizedSpecialNodeView createCategorizedView(CharmSpecialLearning visitedCharm) {
    CategorizedSpecialNodeView specialView = view.createCategorizedSpecialView();
    specialView.setNodeId(visitedCharm.getCharmName().text);
    return specialView;
  }
}