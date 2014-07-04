package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.identifier.Identifier;

public interface DescriptiveFeatures {

  String getPrintName();

  CharacterIdentifier getIdentifier();

  SplatType getTemplateType();

  Identifier getCasteType();

  boolean isDirty();
}
