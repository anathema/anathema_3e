package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.identifier.Identifier;

public interface DescriptiveFeatures {

  String getPrintName();

  HeroIdentifier getIdentifier();

  SplatType getTemplateType();

  Identifier getCasteType();

  boolean isDirty();
}
