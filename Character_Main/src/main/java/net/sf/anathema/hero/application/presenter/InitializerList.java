package net.sf.anathema.hero.application.presenter;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InitializerList {

  private HeroEnvironment environment;

  public InitializerList(HeroEnvironment environment) {
    this.environment = environment;
  }

  public List<HeroModelInitializer> getInOrderFor(HeroModelGroup group) {
    ArrayList<HeroModelInitializer> initializerList = new ArrayList<>();
    Collection<HeroModelInitializer> collection = environment.getObjectFactory().instantiateOrdered(
      RegisteredInitializer.class, environment);
    for (HeroModelInitializer initializer : collection) {
      HeroModelGroup targetGroup = initializer.getClass().getAnnotation(RegisteredInitializer.class).value();
      if (targetGroup.equals(group)) {
        initializerList.add(initializer);
      }
    }
    return initializerList;
  }
}