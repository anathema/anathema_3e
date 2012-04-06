package net.sf.anathema.character.ghost.reporting;

import net.sf.anathema.character.ghost.reporting.rendering.FetterEncoder;
import net.sf.anathema.character.ghost.reporting.rendering.PassionEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractExtendedPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.RegisteredPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.type.CharacterType.GHOST;

@RegisteredPartEncoder(characterType = GHOST)
public class ExtendedGhostPartEncoder extends AbstractExtendedPartEncoder {

  public ExtendedGhostPartEncoder(IResources resources) {
    super(resources);
  }

  @Override
  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportSession session) {
    return new FetterEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportSession reportSession) {
    return new PassionEncoder();
  }
}
