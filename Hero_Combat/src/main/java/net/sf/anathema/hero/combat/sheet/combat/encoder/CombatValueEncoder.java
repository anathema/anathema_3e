package net.sf.anathema.hero.combat.sheet.combat.encoder;

import net.sf.anathema.hero.combat.sheet.combat.content.CombatStatsContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.IContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.LabelledValueEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

public class CombatValueEncoder implements IContentEncoder {

  @Override
  public float encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    CombatStatsContent content = createContent(reportSession);
    Position upperLeft = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(4, upperLeft, bounds.width, 3);
    displayJoinBattleWithSpecialty(graphics, encoder, content);
    displayDodgeWithSpecialty(graphics, encoder, content);
    return encoder.getHeight();
  }

  private CombatStatsContent createContent(ReportSession session) {
    return session.createContent(CombatStatsContent.class);
  }

  private void displayJoinBattleWithSpecialty(SheetGraphics graphics, LabelledValueEncoder encoder, CombatStatsContent content) {
    encoder.addLabelledValue(graphics, 0, content.getJoinLabel(), content.getJoinBattle());
    if (content.getJoinBattle() != content.getJoinBattleWithSpecialty()) {
      encoder.addComment(graphics, content.getJoinBattleSpecialtyLabel(), 0);
    }
  }

  private void displayDodgeWithSpecialty(SheetGraphics graphics, LabelledValueEncoder encoder, CombatStatsContent content) {
    encoder.addLabelledValue(graphics, 1, content.getEvasionLabel(), content.getDodgeDv());
    if (content.getDodgeDv() != content.getEvasionWithSpecialty()) {
      encoder.addComment(graphics, content.getDodgeSpecialtyLabel(), 1);
    }
  }
}
