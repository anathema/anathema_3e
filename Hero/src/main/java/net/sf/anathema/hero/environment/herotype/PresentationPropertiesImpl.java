package net.sf.anathema.hero.environment.herotype;

import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.resources.RelativePath;

public class PresentationPropertiesImpl implements PresentationProperties {

  private final SplatType splatType;

  public PresentationPropertiesImpl(HeroSplat heroSplat) {
    this(heroSplat.getTemplateType());
  }
 
  public PresentationPropertiesImpl(SplatType splatType) {
    this.splatType = splatType;
  }

  @Override
  public RelativePath getSmallCasteIconResource(String casteId) {
    return new RelativePath("icons/" + getCharacterTypeId() + "Button" + casteId + "SecondEdition16.png");
  }

  @Override
  public RelativePath getLargeCasteIconResource(String casteId) {
    return new RelativePath("icons/" + getCharacterTypeId() + "Button" + casteId + "SecondEdition100.png");
  }

  @Override
  public String getNewActionResource() {
    return "CharacterGenerator.Templates." + getCharacterTypeId() + "." + getSubTypeId();
  }

  @Override
  public String getCasteLabelResource() {
    return getCharacterTypeId() + ".Caste.Label";
  }

  private String getCharacterTypeId() {
    return splatType.getCharacterType().getId();
  }

  private String getSubTypeId() {
    return splatType.getSubType().getId();
  }
}