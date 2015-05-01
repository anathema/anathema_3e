package net.sf.anathema.hero.combat.sheet.combat.encoder;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.hero.combat.sheet.combat.content.CombatStatsContent;
import net.sf.anathema.hero.combat.sheet.combat.content.MovementAction;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.TableCell;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.TableList;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

public class CombatRulesTableEncoder extends AbstractCombatRulesTableEncoder {

  @Override
  protected void addFirstCell(SheetGraphics graphics, ReportSession reportSession, PdfPTable table) {
    CombatStatsContent content = reportSession.createContent(CombatStatsContent.class);
    table.addCell(new TableCell(createCombatAttackList(graphics, content), Rectangle.BOX));
  }

  @Override
  protected void addSecondCell(SheetGraphics graphics, ReportSession reportSession, PdfPTable table) {
    CombatStatsContent content = reportSession.createContent(CombatStatsContent.class);
    addAsCell(graphics, table, content.getRulesOfInterest());
  }

  @Override
  protected void addThirdCell(SheetGraphics graphics, ReportSession reportSession, PdfPTable table) {
    CombatStatsContent content = reportSession.createContent(CombatStatsContent.class);
    table.addCell(createCommonActionsTable(graphics, content));
  }

  private PdfPTable createCombatAttackList(SheetGraphics graphics, CombatStatsContent content) {
    TableList list = new TableList(graphics.createCommentFont());
    return list.getTable();
  }

  private PdfPTable createCommonActionsTable(SheetGraphics graphics, CombatStatsContent content) {
    float[] columnWidths = new float[]{5f, 1.5f};
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    table.addCell(createHeaderCell(graphics, columnWidths.length, content.getCombatActionHeader()));
    for (String combatAction : content.getCombatActions()) {
      addCommonActionsCell(graphics, table, combatAction);
      addCommonActionsCell(graphics, table, "");
    }

    addCommonActionsCell(graphics, table, " ");
    addCommonActionsCell(graphics, table, " ");

    table.addCell(createHeaderCell(graphics, columnWidths.length, content.getMovementActionHeader()));
    for (MovementAction movementAction : content.getMovementActions()) {
      addCommonActionsCell(graphics, table, movementAction.name);
      addCommonActionsCell(graphics, table, movementAction.type);
    }
    return table;
  }

  private TableCell createHeaderCell(SheetGraphics graphics, int span, String header) {
    TableCell headerCell = createCommonActionsCell(new Phrase(header, graphics.createTextFont()));
    headerCell.setColspan(span);
    return headerCell;
  }

  private void addCommonActionsCell(SheetGraphics graphics, PdfPTable table, String text) {
    Phrase phrase = new Phrase(text, graphics.createCommentFont());
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    table.addCell(cell);
  }

  private TableCell createCommonActionsCell(Phrase phrase) {
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    return cell;
  }
}
