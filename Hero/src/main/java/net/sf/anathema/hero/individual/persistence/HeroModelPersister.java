package net.sf.anathema.hero.individual.persistence;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.message.Messaging;

public interface HeroModelPersister {

  void setMessaging(Messaging messaging);

  Identifier getModelId();

  void load(Hero hero, HeroModel model, HeroModelLoader loader) throws PersistenceException;

  void save(HeroModel heroModel, HeroModelSaver saver);
}
