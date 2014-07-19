package net.sf.anathema.hero.intimacies.sheet.content;

import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.intimacies.model.Bond;
import net.sf.anathema.hero.intimacies.model.IntimaciesModelImpl;
import net.sf.anathema.hero.intimacies.model.Outlook;
import net.sf.anathema.hero.intimacies.model.Strength;
import net.sf.anathema.lib.dummy.DummyResources;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.hero.intimacies.model.Bond.*;
import static net.sf.anathema.hero.intimacies.model.Outlook.*;
import static net.sf.anathema.hero.intimacies.model.Strength.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleIntimaciesContentTest {
  private IntimaciesModelImpl intimaciesModel = new IntimaciesModelImpl();
  private DummyResources resources = new DummyResources();
  private SimpleIntimaciesContent content;

  @Before
  public void setUp() throws Exception {
    DummyHero hero = new DummyHero();
    hero.addModel(intimaciesModel);
    content = new SimpleIntimaciesContent(hero, resources);
  }

  @Test
  public void doesNotUseSingleLinePerItem() throws Exception {
    assertThat(content.useNewLineForEachEntry(), is(true));
  }

  @Test
  public void abbreviatesPositiveDefiningTieToPlusDT() throws Exception {
    resources.putString("Intimacies.Sheet.Outlook.Positive", "+");
    resources.putString("Intimacies.Sheet.Strength.Defining", "D");
    resources.putString("Intimacies.Sheet.Bond.Tie", "T");
    createIntimacy("Intimacy", Positive, Defining, Tie);
    assertThat(content.getPrintEntries().get(0), is("Intimacy (+DT)"));
  }

  @Test
  public void abbreviatesNegativeToMinus() throws Exception {
    resources.putString("Intimacies.Sheet.Outlook.Negative", "-");
    createIntimacy("Intimacy", Negative, Defining, Tie);
    assertThat(content.getPrintEntries().get(0), containsString("-"));
  }

  @Test
  public void abbreviatesMajorToMa() throws Exception {
    resources.putString("Intimacies.Sheet.Strength.Major", "Ma");
    createIntimacy("Intimacy", Negative, Major, Tie);
    assertThat(content.getPrintEntries().get(0), containsString("Ma"));
  }

  @Test
  public void abbreviatesMinorToMi() throws Exception {
    resources.putString("Intimacies.Sheet.Strength.Minor", "Mi");
    createIntimacy("Intimacy", Negative, Minor, Tie);
    assertThat(content.getPrintEntries().get(0), containsString("Mi"));
  }

  @Test
  public void abbreviatesPrincipleToP() throws Exception {
    resources.putString("Intimacies.Sheet.Bond.Principle", "P");
    createIntimacy("Intimacy", Negative, Minor, Principle);
    assertThat(content.getPrintEntries().get(0), containsString("P"));
  }

  private void createIntimacy(String intimacy, Outlook outlook, Strength strength, Bond bond) {
    intimaciesModel.setCurrentName(intimacy);
    intimaciesModel.setCurrentBond(bond);
    intimaciesModel.setCurrentStrength(strength);
    intimaciesModel.setCurrentOutlook(outlook);
    intimaciesModel.commitSelection();
  }
}