package net.sf.anathema.hero.application.persistence;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.individual.persistence.HeroModelPersister;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.initialization.ObjectFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class HeroPersisterList {

  private final HeroModelPersisterAutoCollector persisterAutoCollector;

  public HeroPersisterList(ObjectFactory objectFactory) {
    this.persisterAutoCollector = new HeroModelPersisterAutoCollector(objectFactory);
  }

  public Iterable<HeroModelPersister> iterator(Hero hero) {
    Collection<HeroModelPersister> allPersisters = persisterAutoCollector.collect();
    List<HeroModelPersister> heroPersisters = new ArrayList<>();
    for (HeroModel model : hero) {
      Collection<HeroModelPersister> foundPersisters = findPersisters(allPersisters, model.getId());
      heroPersisters.addAll(foundPersisters);
    }
    return heroPersisters;
  }

  private Collection<HeroModelPersister> findPersisters(Collection<HeroModelPersister> allPersisters, Identifier modelId) {
    return allPersisters.stream().filter(persister -> persister.getModelId().equals(modelId)).collect(toList());
  }
}