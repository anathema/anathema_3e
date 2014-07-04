package net.sf.anathema.integration;

import net.sf.anathema.TestInitializer;
import net.sf.anathema.hero.application.HeroEnvironmentExtractor;
import net.sf.anathema.hero.application.item.HeroItem;
import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.item.Item;
import net.sf.anathema.hero.application.persistence.HeroItemPersister;
import net.sf.anathema.hero.application.persistence.RepositoryItemPersister;
import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;
import net.sf.anathema.hero.application.perspective.model.CharacterPersistenceModel;
import net.sf.anathema.hero.display.fx.perspective.CharacterSystemInitializer;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.environment.template.SplatTypeImpl;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.io.InputOutput;
import net.sf.anathema.platform.frame.ApplicationModel;

import java.io.File;

public class CharacterFactory {

  private HeroTypes heroTypes;
  private ApplicationModel model;
  private HeroEnvironment heroEnvironment;

  public void startAnathema() {
    TestInitializer initializer = TestInitializer.Create();
    this.model = initializer.initialize();
    new CharacterSystemInitializer(model,initializer.getEnvironment()).initializeCharacterSystem();
    heroEnvironment = HeroEnvironmentExtractor.getGenerics(model);
    this.heroTypes = heroEnvironment.getHeroTypes();
  }

  public HeroItemData createCharacter(String type, String subtype) {
    HeroSplat characterTemplate = loadTemplateForType(type, subtype);
    return createCharacter(characterTemplate);
  }

  public HeroItemData saveAndReload(HeroItemData heroItemData) throws  Exception{
    CharacterPersistenceModel persistenceModel = new CharacterPersistenceModel(model, heroEnvironment);
    HeroItem heroItem = new HeroItem(heroItemData);
    persistenceModel.save(heroItem);
    String repositoryId = heroItem.getRepositoryLocation().getId();
    Item loadItem = persistenceModel.loadItem(new CharacterIdentifier(repositoryId));
    return (HeroItemData) loadItem.getItemData();
  }

  private HeroSplat loadTemplateForType(String type, String subtype) {
    HeroEnvironment generics = getCharacterGenerics();
    return generics.getTemplateRegistry().getTemplate(new SplatTypeImpl(heroTypes.findById(type), new SimpleIdentifier(subtype)));
  }

  private HeroItemData createCharacter(HeroSplat template) {
    RepositoryItemPersister itemPersister = new HeroItemPersister(getCharacterGenerics(), model.getMessaging());
    Item item = itemPersister.createNew(template);
    return (HeroItemData) item.getItemData();
  }

  private HeroEnvironment getCharacterGenerics() {
    return HeroEnvironmentExtractor.getGenerics(model);
  }

  public void tearDownRepository() throws Throwable{
    File repositoryDirectory = new File(model.getRepository().getRepositoryPath());
      if (repositoryDirectory.exists()) {
        InputOutput.deleteDirectory(repositoryDirectory);
      }
   }
}
