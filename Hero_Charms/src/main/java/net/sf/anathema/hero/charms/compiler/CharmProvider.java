package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;

import java.util.Collection;
import java.util.List;

public interface CharmProvider {

  List<CategoryReference> getAllCategories();

  List<Charm> getCharms(CategoryReference category);

  List<CharmSpecialLearning> getSpecialLearningCharms(CategoryReference category);
  
  Collection<Charm> getCharmsWithSpecialMechanics();
  
  Collection<CharmSpecialMechanic> getSpecialMechanicsForCharm(CharmName name);
}