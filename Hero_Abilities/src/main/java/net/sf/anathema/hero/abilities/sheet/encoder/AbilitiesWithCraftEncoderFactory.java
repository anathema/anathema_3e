package net.sf.anathema.hero.abilities.sheet.encoder;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.abilities.sheet.content.AbilitiesContent;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;
import net.sf.anathema.hero.traits.sheet.encoder.FavorableTraitContentEncoder;

@SuppressWarnings("UnusedDeclaration")
public class AbilitiesWithCraftEncoderFactory extends GlobalEncoderFactory {

  public AbilitiesWithCraftEncoderFactory() {
    super(EncoderIds.ABILITIES_WITH_CRAFTS);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    FavorableTraitContentEncoder<AbilitiesContent> encoder = new FavorableTraitContentEncoder<>(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    // todo bring back crafts ???
    //encoder.addAdditionalEncoder(new SpecialtiesEncoder(resources, traitEncoder, 9));
    return encoder;
  }
}
