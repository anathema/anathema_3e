package net.sf.anathema.hero.sheet.pdf.page.layout.simple;

import net.sf.anathema.hero.sheet.pdf.encoder.CopyrightEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.page.PageConfiguration;
import net.sf.anathema.hero.sheet.pdf.page.PageEncoder;
import net.sf.anathema.hero.sheet.pdf.page.layout.Sheet;
import net.sf.anathema.hero.sheet.pdf.page.layout.SheetPage;
import net.sf.anathema.hero.sheet.pdf.page.layout.field.LayoutField;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.*;

public class FirstPageEncoder implements PageEncoder {

  private static final float ANIMA_HEIGHT = 128;
  private static final int ATTRIBUTE_HEIGHT = 128;
  private static final int COMBAT_HEIGHT = 118;
  private static final int FIRST_ROW_HEIGHT = 51;
  private static final int SOCIAL_COMBAT_HEIGHT = 115;
  private static final int WILLPOWER_HEIGHT = 43;
  private static final int ARMOUR_HEIGHT = 68;
  private static final int HEALTH_HEIGHT = 99;
  private final PageConfiguration configuration;

  public FirstPageEncoder(PageConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public void encode(Sheet sheet, SheetGraphics graphics, ReportSession session) {
    SheetPage page = sheet.startPortraitPage(graphics, session, FIRST_PAGE_CONTENT_HEIGHT);
    LayoutField personalInfo = page.place(PERSONAL_INFO).atStartOf(page).withHeight(FIRST_ROW_HEIGHT).andColumnSpan(2).now();
    LayoutField essence = page.place(ESSENCE_SIMPLE).rightOf(personalInfo).withSameHeight().now();

    LayoutField attributes = page.place(ATTRIBUTES).below(personalInfo).withHeight(ATTRIBUTE_HEIGHT).now();
    LayoutField social = page.place(SOCIAL_COMBAT).rightOf(attributes).withHeight(SOCIAL_COMBAT_HEIGHT).now();

    LayoutField willpower = page.place(WILLPOWER_SIMPLE).below(essence).withHeight(WILLPOWER_HEIGHT).now();
    LayoutField greatCurse = page.place(GREAT_CURSE, MUTATIONS).below(willpower).alignBottomTo(social).now();
    LayoutField anima = page.place(ANIMA, NOTES).below(greatCurse).withHeight(ANIMA_HEIGHT).now();

    LayoutField intimacies = page.place(INTIMACIES_SIMPLE, NOTES).below(social).alignBottomTo(anima).now();

    LayoutField combat = page.place(COMBAT).below(intimacies).withHeight(COMBAT_HEIGHT).andColumnSpan(2).now();
    LayoutField health = page.place(HEALTH_AND_MOVEMENT).below(combat).withHeight(HEALTH_HEIGHT).andColumnSpan(2).now();
    LayoutField panoply = page.place(PANOPLY).below(health).withHeight(ARMOUR_HEIGHT).andColumnSpan(2).now();
    page.place(ARSENAL).below(panoply).fillToBottomOfPage().andColumnSpan(2).now();

    LayoutField abilities = page.place(ABILITIES_WITH_CRAFTS).below(attributes).alignBottomTo(health).now();
    page.place(SPECIALTIES_SIMPLE).below(abilities).fillToBottomOfPage().now();

    new CopyrightEncoder(configuration, FIRST_PAGE_CONTENT_HEIGHT).encodeCopyright(graphics);
  }
}
