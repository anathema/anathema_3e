package net.sf.anathema.hero.application.perspective.model;

import net.sf.anathema.hero.application.item.Item;

import java.util.Collection;

public interface ItemSystemModel extends ItemSelectionModel {

  Collection<CharacterItemModel> collectAllExistingCharacters();

  Item loadItem(CharacterIdentifier identifier);

  void setCurrentCharacter(CharacterIdentifier identifier);
}