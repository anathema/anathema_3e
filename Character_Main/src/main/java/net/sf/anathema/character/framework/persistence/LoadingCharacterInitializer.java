package net.sf.anathema.character.framework.persistence;

import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.persistence.HeroModelPersister;
import net.sf.anathema.lib.util.Identifier;

public class LoadingCharacterInitializer implements CharacterInitializer{
  private final RepositoryReadAccess readAccess;
  private final HeroPersisterList persisterList;
  private final IMessaging messaging;

  public LoadingCharacterInitializer(RepositoryReadAccess readAccess, HeroPersisterList persisterList,
                                     IMessaging messaging) {
    this.readAccess = readAccess;
    this.persisterList = persisterList;
    this.messaging = messaging;
  }

  public void initialize(Hero hero) {
    for (HeroModelPersister persister : persisterList.iterator(hero)) {
      Identifier modelId = persister.getModelId();
      HeroModel heroModel = hero.getModel(modelId);
      if (heroModel != null) {
        persister.setMessaging(messaging);
        persister.load(hero, heroModel, new HeroModelLoaderImpl(readAccess));
      }
    }
  }
}
