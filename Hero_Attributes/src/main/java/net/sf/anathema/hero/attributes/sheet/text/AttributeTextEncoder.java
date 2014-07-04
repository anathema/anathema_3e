package net.sf.anathema.hero.attributes.sheet.text;

import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.sheet.encoder.AbstractTraitTextEncoder;
import net.sf.anathema.library.resources.Resources;

public class AttributeTextEncoder extends AbstractTraitTextEncoder {

  public AttributeTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  @Override
  protected TraitType[] getTypes(Hero hero) {
    return AttributeType.values();
  }

  @Override
  protected String getLabelKey() {
    return "TextDescription.Label.Attributes";
  }
}
