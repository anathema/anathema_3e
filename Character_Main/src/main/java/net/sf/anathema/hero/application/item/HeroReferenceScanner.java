package net.sf.anathema.hero.application.item;

import net.sf.anathema.hero.application.perspective.model.CharacterReference;
import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.identifier.Identifier;

public interface HeroReferenceScanner {
  CharacterType getCharacterType(CharacterReference reference);

  Identifier getCasteType(CharacterReference reference);

  SplatType getTemplateType(CharacterReference reference);
}