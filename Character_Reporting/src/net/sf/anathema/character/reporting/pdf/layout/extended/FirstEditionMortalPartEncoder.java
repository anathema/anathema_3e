package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.PdfExperienceEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfDotsEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionMortalPartEncoder extends AbstractFirstEditionPartEncoder {

  private final ExtendedEncodingRegistry registry;

  public FirstEditionMortalPartEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont, ExtendedEncodingRegistry registry) {
    super(resources, baseFont, symbolBaseFont);
    this.registry = registry;
  }

  public IBoxContentEncoder getAnimaEncoder() {
    return new PdfBackgroundEncoder(getResources(), getBaseFont());
  }

  public IBoxContentEncoder getEssenceEncoder() {
    return new PdfExperienceEncoder(getResources(), getBaseFont());
  }

  public IBoxContentEncoder getDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey) {
    return new PdfDotsEncoder(getBaseFont(), getResources(), trait, traitMax, traitHeaderKey);
  }

  public boolean hasSecondPage() {
    return false;
  }

  public boolean hasMagicPage() {
    return false;
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return registry.getLinguisticsEncoder(); //No Great Curse for Mortals
  }
}
