package net.sf.anathema.hero.persistence;

import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.identifier.Identifier;

public interface HeroModelPersister {

  void setMessaging(Messaging messaging);

  Identifier getModelId();

  void load(Hero hero, HeroModel model, HeroModelLoader loader) throws PersistenceException;

  void save(HeroModel heroModel, HeroModelSaver saver);
}
