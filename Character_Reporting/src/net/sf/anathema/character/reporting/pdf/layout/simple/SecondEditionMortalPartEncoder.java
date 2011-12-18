package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionMortalPartEncoder extends AbstractSecondEditionPartEncoder {

  public SecondEditionMortalPartEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    super(resources, baseFont, symbolBaseFont, 3);
  }

  public IBoxContentEncoder getAnimaEncoder() {
    return null;
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return null;
  }

  public boolean hasMagicPage() {
    return false;
  }
}
