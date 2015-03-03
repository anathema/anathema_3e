package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.platform.tree.display.TreeView;

import com.google.common.base.Predicate;

public interface SpecialCharmList {
  void add(CharmSpecialLearning charm);

  void showViews();

  void setVisibilityPredicate(Predicate<String> predicate);

  void operateOn(TreeView treeView);
}
