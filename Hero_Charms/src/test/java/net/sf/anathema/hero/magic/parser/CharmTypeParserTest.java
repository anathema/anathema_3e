package net.sf.anathema.hero.magic.parser;

import net.sf.anathema.hero.magic.charm.CharmException;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.magic.parser.charms.CharmTypeParser;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharmTypeParserTest {

  private CharmTypeParser builder = new CharmTypeParser();

  @Test(expected = CharmException.class)
  public void testTypeRequired() throws Exception {
    String xml = "<charm></charm>";
    Element element = DocumentUtilities.read(xml).getRootElement();
    builder.build(element);
  }

  @Test(expected = CharmException.class)
  public void testMangledType() throws Exception {
    String xml = "<charm><charmtype type=\"simp\"/></charm>";
    Element element = DocumentUtilities.read(xml).getRootElement();
    builder.build(element);
  }

  @Test
  public void buildsSupplementalType() throws Exception {
    String xml = "<charm><charmtype type=\"Supplemental\"/></charm>";
    Element element = DocumentUtilities.read(xml).getRootElement();
    CharmType charmType = builder.build(element);
    assertEquals(CharmType.Supplemental, charmType);
  }

  @Test
  public void buildsExtraActionType() throws Exception {
    String xml = "<charm><charmtype type=\"ExtraAction\"></charmtype></charm>";
    Element element = DocumentUtilities.read(xml).getRootElement();
    CharmType charmType = builder.build(element);
    assertEquals(CharmType.ExtraAction, charmType);
  }

  @Test
  public void buildsReflexiveType() throws Exception {
    String xml = "<charm><charmtype type=\"Reflexive\"></charmtype></charm>";
    Element element = DocumentUtilities.read(xml).getRootElement();
    CharmType charmType = builder.build(element);
    assertEquals(CharmType.Reflexive, charmType);
  }

  @Test
  public void testSimpleWithoutSpecial() throws Exception {
    String xml = "<charm><charmtype type=\"Simple\"></charmtype></charm>";
    Element element = DocumentUtilities.read(xml).getRootElement();
    CharmType charmType = builder.build(element);
    assertEquals(CharmType.Simple, charmType);
  }
}