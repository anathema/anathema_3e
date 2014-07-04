package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.application.item.HeroReferenceScanner;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.hero.framework.perspective.model.CharacterReference;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.identifier.Identifier;

public class PreloadedDescriptiveFeatures implements DescriptiveFeatures {

  private final HeroReferenceScanner fileScanner;
  private final CharacterReference reference;

  public PreloadedDescriptiveFeatures(HeroReferenceScanner fileScanner, CharacterReference reference) {
    this.fileScanner = fileScanner;
    this.reference = reference;
  }

  @Override
  public String getPrintName() {
    return reference.printName;
  }

  @Override
  public CharacterIdentifier getIdentifier() {
    String repositoryId = reference.repositoryId.getStringRepresentation();
    return new CharacterIdentifier(repositoryId);
  }

  @Override
  public SplatType getTemplateType() {
    return fileScanner.getTemplateType(reference);
  }

  @Override
  public Identifier getCasteType() {
    return fileScanner.getCasteType(reference);
  }

  @Override
  public boolean isDirty() {
    return false;
  }
}
