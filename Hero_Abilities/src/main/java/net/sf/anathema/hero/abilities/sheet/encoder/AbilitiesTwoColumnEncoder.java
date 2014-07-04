package net.sf.anathema.hero.abilities.sheet.encoder;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.abilities.sheet.content.AbilitiesContent;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.traits.sheet.encoder.FavorableTraitContentEncoder;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.PADDING;

public class AbilitiesTwoColumnEncoder extends FavorableTraitContentEncoder<AbilitiesContent> {

  private Resources resources;

  public AbilitiesTwoColumnEncoder(Resources resources) {
    super(AbilitiesContent.class);
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    float columnWidth = (bounds.getWidth() - PADDING - PADDING) / 2f;
    Bounds firstColumnBounds = new Bounds(bounds.x, bounds.y, columnWidth, bounds.height);
    super.encode(graphics, reportSession, firstColumnBounds);
    float secondColumnX = bounds.x + columnWidth + PADDING + PADDING;
    float craftHeight = bounds.height * 2f / 5f;
    // Encode crafts
  }
}
