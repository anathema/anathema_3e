package net.sf.anathema.hero.application.presenter;

import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.platform.frame.ApplicationModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InitializerList {

  private final ApplicationModel applicationModel;
  private final ObjectFactory objectFactory;

  public InitializerList(ObjectFactory objectFactory, ApplicationModel applicationModel) {
    this.objectFactory = objectFactory;
    this.applicationModel = applicationModel;
  }

  public List<HeroModelInitializer> getInOrderFor(HeroModelGroup group) {
    ArrayList<HeroModelInitializer> initializerList = new ArrayList<>();
    Collection<HeroModelInitializer> collection = objectFactory.instantiateOrdered(RegisteredInitializer.class, applicationModel);
    for (HeroModelInitializer initializer : collection) {
      HeroModelGroup targetGroup = initializer.getClass().getAnnotation(RegisteredInitializer.class).value();
      if (targetGroup.equals(group)) {
        initializerList.add(initializer);
      }
    }
    return initializerList;
  }
}