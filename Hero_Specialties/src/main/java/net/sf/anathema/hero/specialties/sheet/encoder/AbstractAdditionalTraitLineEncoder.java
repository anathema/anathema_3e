package net.sf.anathema.hero.specialties.sheet.encoder;

import com.itextpdf.text.pdf.PdfContentByte;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.specialties.sheet.content.NamedSpecialtyReference;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;
import net.sf.anathema.hero.traits.sheet.content.TraitReferenceI18n;
import net.sf.anathema.hero.traits.sheet.content.ValuedTraitReference;
import net.sf.anathema.library.resources.Resources;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.SUBSECTION_FONT_SIZE;

public abstract class AbstractAdditionalTraitLineEncoder {
  private final Resources resources;
  private final PdfTraitEncoder traitEncoder;

  public AbstractAdditionalTraitLineEncoder(Resources resources, PdfTraitEncoder traitEncoder) {
    this.resources = resources;
    this.traitEncoder = traitEncoder;
  }

  protected Resources getResources() {
    return resources;
  }

  protected int getLineCount(String title, float height) {
    if (title != null) {
      height -= SUBSECTION_FONT_SIZE * 1.5f;
    }
    return (int) (height / traitEncoder.getTraitHeight());
  }

  protected float drawNamedTraitSection(SheetGraphics graphics, String title, TraitReferences traits, Position position, float width, float height, int dotCount) {
    int lineCount = getLineCount(title, height);
    float height1 = 0;
    if (title != null) {
      height1 = drawSubsectionHeader(graphics, title, position, width);
    }
    TraitReferenceI18n internationalizer = new TraitReferenceI18n(getResources());
    for (ValuedTraitReference trait : traits.forElementsFromOneTo(lineCount)) {
      String name = internationalizer.getSheetName(trait);
      Position traitPosition = new Position(position.x, position.y - height1);
      int value = trait.getValue();
      traitEncoder.encodeWithText(graphics, name, traitPosition, width, value, dotCount);
      height1 += traitEncoder.getTraitHeight();
    }
    for (int index = traits.countTraits(); index < lineCount; index++) {
      Position traitPosition = new Position(position.x, position.y - height1);
      traitEncoder.encodeWithLine(graphics, traitPosition, width, 0, dotCount);
      height1 += traitEncoder.getTraitHeight();
    }
    return height1;
  }

  private float drawSubsectionHeader(SheetGraphics graphics, String text, Position position, float width) {
    setSubsectionFont(graphics);
    graphics.drawText(text, new Position(position.x + width / 2, position.y), PdfContentByte.ALIGN_CENTER);
    return SUBSECTION_FONT_SIZE * 1.5f;
  }

  protected final void setSubsectionFont(SheetGraphics graphics) {
    graphics.setSubsectionFont();
  }

  protected final List<ValuedTraitReference> getTraitReferences(Collection<Specialty> specialties, TraitType type) {
    Stream<Specialty> traits = specialties.stream();
    return traits.map(trait -> new NamedSpecialtyReference(trait, type)).collect(toList());
  }
}
