package net.sf.anathema.hero.specialties.sheet.encoder;

import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.specialties.sheet.content.SpecialtiesContentCandidate;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;
import net.sf.anathema.hero.traits.sheet.content.ValuedTraitReference;
import net.sf.anathema.library.resources.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.BARE_LINE_HEIGHT;
import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.PADDING;
import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.TEXT_PADDING;

public class ExtendedSpecialtiesEncoder extends AbstractAdditionalTraitLineEncoder implements ContentEncoder {

  public ExtendedSpecialtiesEncoder(Resources resources) {
    super(resources, PdfTraitEncoder.createSmallTraitEncoder());
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    SpecialtiesContentCandidate content = new SpecialtiesContentCandidate(reportSession.getHero());
    List<ValuedTraitReference> specialties = new ArrayList<>();
    for (IdentifiedTraitTypeList group : AbilitiesModelFetcher.fetch(reportSession.getHero()).getGroups()) {
      for (TraitType traitType : group.getAll()) {
        specialties.addAll(getTraitReferences(content.getSpecialties(traitType), traitType));
      }
    }

    int lineCount = getLineCount(null, bounds.height);
    TraitReferences leftSpecialties = new TraitReferences(specialties.subList(0, Math.min(specialties.size(), lineCount)));
    TraitReferences rightSpecialties = new TraitReferences(specialties.subList(leftSpecialties.countTraits(), specialties.size()));

    float columnWidth = (bounds.width - PADDING) / 2f;
    float columnHeight = bounds.height - TEXT_PADDING / 2f;
    float yPosition = bounds.getMaxY() - BARE_LINE_HEIGHT;

    float leftPosition = bounds.getMinX();
    drawNamedTraitSection(graphics, null, leftSpecialties, new Position(leftPosition, yPosition), columnWidth, columnHeight, 3);

    float rightPosition = leftPosition + columnWidth + PADDING;
    drawNamedTraitSection(graphics, null, rightSpecialties, new Position(rightPosition, yPosition), columnWidth, columnHeight, 3);
  }

  @Override
  public String getHeader(ReportSession session) {
    return getResources().getString("Sheet.Header.Specialties");
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
