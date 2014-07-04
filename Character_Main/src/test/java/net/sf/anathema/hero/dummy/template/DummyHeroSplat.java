package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.hero.environment.template.SplatTypeImpl;
import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.hero.individual.splat.ConfiguredModel;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;

public class DummyHeroSplat implements HeroSplat {

  public SplatType type = new SplatTypeImpl(new DummyMundaneCharacterType(), new SimpleIdentifier("Test"));

  @Override
  public SplatType getTemplateType() {
    return type;
  }

  @Override
  public List<ConfiguredModel> getModels() {
    return new ArrayList<>();
  }

  public void setCharacterType(CharacterType characterType) {
    this.type = new SplatTypeImpl(characterType, type.getSubType());
  }

  public void setSubType(String subtype) {
    this.type = new SplatTypeImpl(type.getCharacterType(), new SimpleIdentifier(subtype));
  }
}