package net.sf.anathema.hero.framework.perspective.model;

import net.sf.anathema.hero.application.item.HeroItemTypeRetrieval;
import net.sf.anathema.hero.application.item.HeroRepositoryData;
import net.sf.anathema.hero.application.item.Item;
import net.sf.anathema.hero.application.item.ItemRepositoryLocation;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.framework.persistence.HeroItemPersister;
import net.sf.anathema.hero.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.repository.Repository;
import net.sf.anathema.platform.repository.RepositoryIdData;
import net.sf.anathema.platform.repository.access.RepositoryReadAccess;
import net.sf.anathema.platform.repository.access.RepositoryWriteAccess;
import net.sf.anathema.platform.repository.printname.ReferenceAccess;
import net.sf.anathema.platform.repository.printname.ReferenceBuilder;

import java.io.IOException;
import java.util.Collection;

import static net.sf.anathema.hero.application.item.HeroItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterPersistenceModel {

  private ApplicationModel model;
  private HeroEnvironment heroEnvironment;

  public CharacterPersistenceModel(ApplicationModel model, HeroEnvironment environment) {
    this.model = model;
    this.heroEnvironment = environment;
  }

  public Collection<CharacterReference> collectCharacters() {
    ReferenceBuilder<CharacterReference> builder = new CharacterReferenceBuilder();
    ReferenceAccess<CharacterReference> access = model.getRepository().createReferenceAccess(retrieveCharacterItemType(), builder);
    return access.collectAllItemReferences();
  }

  public Item loadItem(CharacterIdentifier identifier) {
    RepositoryReadAccess readAccess = createReadAccess(identifier.getId());
    RepositoryItemPersister persister = findPersister();
    return persister.load(readAccess);
  }

  public void save(Item item) throws IOException {
    RepositoryItemPersister persister = findPersister();
    assignUniqueIdAsRequired(item);
    RepositoryWriteAccess writeAccess = createWriteAccessFor(item);
    persister.save(writeAccess, item);
  }

  private void assignUniqueIdAsRequired(Item item) {
    ItemRepositoryLocation repositoryLocation = item.getRepositoryLocation();
    if (repositoryLocation.requiresId()) {
      Repository repository = model.getRepository();
      RepositoryIdData data = new HeroRepositoryData((Hero) item.getItemData());
      String id = repository.createUniqueRepositoryId(data);
      repositoryLocation.setId(id);
    }
  }

  private RepositoryWriteAccess createWriteAccessFor(Item item) {
    return model.getRepository().createWriteAccess(HeroItemTypeRetrieval.retrieveCharacterItemType(), item.getRepositoryLocation().getId());
  }

  private RepositoryItemPersister findPersister() {
    HeroEnvironment generics = heroEnvironment;
    return new HeroItemPersister(generics, model.getMessaging());
  }

  private RepositoryReadAccess createReadAccess(String repositoryId) {
    Repository repository = model.getRepository();
    return repository.openReadAccess(getCharacterItemType(), repositoryId);
  }

  private IItemType getCharacterItemType() {
    return retrieveCharacterItemType();
  }
}
