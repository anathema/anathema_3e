package net.sf.anathema.integration;

import net.sf.anathema.TestInitializer;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.fx.hero.perspective.CharacterSystemInitializer;
import net.sf.anathema.hero.framework.*;
import net.sf.anathema.hero.framework.Character;
import net.sf.anathema.hero.framework.item.Item;
import net.sf.anathema.hero.framework.persistence.HeroItemPersister;
import net.sf.anathema.hero.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.hero.template.TemplateTypeImpl;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class CharacterFactory {

  private CharacterTypes characterTypes;
  private IApplicationModel model;

  public void startAnathema() {
    TestInitializer initializer = TestInitializer.Create();
    this.model = initializer.initialize();
    new CharacterSystemInitializer(model,initializer.getEnvironment()).initializeCharacterSystem();
    this.characterTypes = HeroEnvironmentExtractor.getGenerics(model).getCharacterTypes();

  }

  public Character createCharacter(String type, String subtype) {
    HeroTemplate characterTemplate = loadTemplateForType(type, subtype);
    return createCharacter(characterTemplate);
  }

  private HeroTemplate loadTemplateForType(String type, String subtype) {
    HeroEnvironment generics = getCharacterGenerics();
    return generics.getTemplateRegistry().getTemplate(new TemplateTypeImpl(characterTypes.findById(type), new SimpleIdentifier(subtype)));
  }

  private Character createCharacter(HeroTemplate template) {
    RepositoryItemPersister itemPersister = new HeroItemPersister(getCharacterGenerics(), model.getMessaging());
    Item item = itemPersister.createNew(template);
    return (Character) item.getItemData();
  }

  private HeroEnvironment getCharacterGenerics() {
    return HeroEnvironmentExtractor.getGenerics(model);
  }
}
