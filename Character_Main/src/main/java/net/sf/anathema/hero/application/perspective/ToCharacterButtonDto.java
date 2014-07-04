package net.sf.anathema.hero.application.perspective;

import com.google.common.base.Function;
import net.sf.anathema.hero.application.creation.GenericPresentationTemplate;
import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;
import net.sf.anathema.hero.framework.CharacterUI;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;

import static net.sf.anathema.hero.elsewhere.concept.CasteType.NULL_CASTE_TYPE;

public class ToCharacterButtonDto implements Function<DescriptiveFeatures, CharacterButtonDto> {
  private final Resources resources;

  public ToCharacterButtonDto(Resources resources) {
    this.resources = resources;
  }

  @Override
  public CharacterButtonDto apply(DescriptiveFeatures input) {
    String text = input.getPrintName();
    CharacterIdentifier identifier = input.getIdentifier();
    SplatType splatType = input.getTemplateType();
    Identifier casteType = input.getCasteType();
    String details = getDetails(splatType);
    RelativePath pathToImage = getPathToImage(splatType, casteType);
    boolean dirty = input.isDirty();
    return new CharacterButtonDto(identifier, text, details, pathToImage, dirty);
  }

  private String getDetails(SplatType splatType) {
    GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate(splatType);
    return resources.getString(presentationTemplate.getNewActionResource());
  }

  private RelativePath getPathToImage(SplatType splatType, Identifier casteType) {
    if (casteType == NULL_CASTE_TYPE) {
      return new CharacterUI().getLargeTypeIconPath(splatType.getCharacterType());
    } else {
      GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate(splatType);
      return presentationTemplate.getLargeCasteIconResource(casteType.getId());
    }
  }
}
