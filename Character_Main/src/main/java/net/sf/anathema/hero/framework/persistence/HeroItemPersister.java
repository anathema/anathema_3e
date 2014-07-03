package net.sf.anathema.hero.framework.persistence;

import net.sf.anathema.framework.messaging.MessageToken;
import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.framework.repository.access.RepositoryWriteAccess;
import net.sf.anathema.hero.application.item.HeroItemImp;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.item.CharacterItem;
import net.sf.anathema.hero.framework.item.HeroNameFetcher;
import net.sf.anathema.hero.framework.item.Item;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.persistence.HeroModelPersister;
import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.hero.template.TemplateTypeImpl;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import static net.sf.anathema.lib.message.MessageType.Information;

public class HeroItemPersister implements RepositoryItemPersister {

  private final HeroEnvironment generics;
  private final Messaging messaging;
  private final HeroPersisterList persisterList;

  public HeroItemPersister(HeroEnvironment generics, Messaging messaging) {
    this.generics = generics;
    this.messaging = messaging;
    this.persisterList = new HeroPersisterList(generics.getObjectFactory());
  }

  @Override
  public Item createNew(HeroTemplate template) throws PersistenceException {
    return createCharacterInItem(template, new NewCharacterInitializer());
  }

  @Override
  public void save(RepositoryWriteAccess writeAccess, Item item) throws PersistenceException {
    Hero hero = (Hero) item.getItemData();
    String name = new HeroNameFetcher().getName(hero);
    MessageToken token = messaging.addMessage(Information, "CharacterPersistence.SavingCharacter", name);
    saveModels(writeAccess, hero);
    new HeroMainFilePersister().save(writeAccess, item);
    token.replaceMessage(Information, "CharacterPersistence.SavingCharacterDone", name);
  }

  @Override
  public Item load(RepositoryReadAccess readAccess) throws PersistenceException {
    HeroMainFileDto mainFileDto = new HeroMainFilePersister().load(readAccess);
    HeroTemplate template = loadHeroTemplate(mainFileDto);
    CharacterInitializer initializer = new LoadingCharacterInitializer(readAccess, persisterList, messaging);
    Item item = createCharacterInItem(template, initializer);
    item.getRepositoryLocation().setId(mainFileDto.repositoryId);
    return item;
  }

  private HeroTemplate loadHeroTemplate(HeroMainFileDto mainFileDto) {
    CharacterType characterType = generics.getCharacterTypes().findById(mainFileDto.characterType.characterType);
    Identifier subtype = new SimpleIdentifier(mainFileDto.characterType.subType);
    TemplateTypeImpl templateType = new TemplateTypeImpl(characterType, subtype);
    return generics.getTemplateRegistry().getTemplate(templateType);
  }

  private void saveModels(RepositoryWriteAccess writeAccess, Hero hero) {
    for (HeroModelPersister persister : persisterList.iterator(hero)) {
      HeroModel heroModel = hero.getModel(persister.getModelId());
      if (heroModel != null) {
        persister.setMessaging(messaging);
        persister.save(heroModel, new HeroModelSaverImpl(writeAccess));
      }
    }
  }

  private Item createCharacterInItem(HeroTemplate template, CharacterInitializer initializer) {
    HeroItemImp character = new HeroItemImp(template, generics);
    initializer.initialize(character);
    return initItem(character);
  }

  private Item initItem(HeroItemImp character) {
    character.markReadyForWork();
    return new CharacterItem(character);
  }
}