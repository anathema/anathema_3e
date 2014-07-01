package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.getTreeCategory;

public class CharacterTypeList implements Iterable<CharacterType> {
  private final List<CharacterType> availableTypes = new ArrayList<>();
  private CharmProvider charmProvider;

  public CharacterTypeList(CharmProvider charmProvider) {
    this.charmProvider = charmProvider;
  }

  public void collectAvailableTypes(CharacterType nativeCharacterType, CharacterTypes characterTypes) {
    for (CharacterType type : characterTypes) {
      if (charmProvider.getCharms(getTreeCategory(type)).length > 0) {
        availableTypes.add(type);
      }
    }
    availableTypes.remove(nativeCharacterType);
    availableTypes.add(0, nativeCharacterType);
  }

  @Override
  public Iterator<CharacterType> iterator() {
    return availableTypes.iterator();
  }

  public CharacterType[] asArray() {
    return availableTypes.toArray(new CharacterType[availableTypes.size()]);
  }
}