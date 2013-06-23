package net.sf.anathema.character.perspective;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.perspective.model.CharacterIdentifier;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.util.Identifier;

public class LoadedDescriptiveFeatures implements DescriptiveFeatures {

  private CharacterIdentifier identifier;
  private IItem characterItem;

  public LoadedDescriptiveFeatures(CharacterIdentifier identifier, IItem characterItem) {
    this.identifier = identifier;
    this.characterItem = characterItem;
  }

  @Override
  public String getPrintName() {
    return characterItem.getDisplayName();
  }

  @Override
  public CharacterIdentifier getIdentifier() {
    return identifier;
  }

  @Override
  public ITemplateType getTemplateType() {
    Hero hero = (Hero) characterItem.getItemData();
    return hero.getTemplate().getTemplateType();
  }

  @Override
  public Identifier getCasteType() {
    Hero hero = (Hero) characterItem.getItemData();
    return HeroConceptFetcher.fetch(hero).getCaste().getType();
  }

  @Override
  public boolean isDirty() {
    return characterItem.isDirty();
  }
}
