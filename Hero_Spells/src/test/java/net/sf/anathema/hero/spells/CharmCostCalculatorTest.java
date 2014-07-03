package net.sf.anathema.hero.spells;

import com.google.common.collect.ImmutableList;
import net.sf.anathema.hero.charms.CharmHeroObjectMother;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzerImpl;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostCalculator;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostEvaluator;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationData;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelImpl;
import net.sf.anathema.hero.charms.model.favored.FavoredChecker;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.magic.DummySpell;
import net.sf.anathema.magic.Magic;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.spells.Spell;
import net.sf.anathema.hero.traits.model.context.CreationTraitValueStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CharmCostCalculatorTest {

  private MagicCreationCostCalculator calculator;
  private DummySpellsModel spells = new DummySpellsModel();
  private CharmsModel charmModel = new CharmsModelImpl(new CharmsTemplate());

  @Before
  public void setUp() throws Exception {
    spells.initializeMagicModel(charmModel);
    CharmHeroObjectMother objectMother = new CharmHeroObjectMother();
    CreationTraitValueStrategy valueStrategy = new CreationTraitValueStrategy();
    DummyHero hero = objectMother.createModelContextWithEssence2(valueStrategy);
    hero.addModel(charmModel);
    hero.addModel(spells);
    MagicPointsTemplate template = new MagicPointsTemplate();
    template.generalCreationPoints.freePicks = 3;
    template.generalCreationPoints.costs = 5;
    template.favoredCreationPoints.freePicks = 2;
    template.favoredCreationPoints.costs = 4;
    MagicCreationCostEvaluator magicCostEvaluator = charmModel.getMagicCostEvaluator();
    MagicCreationData creationData = new MagicCreationData(template, MartialArtsLevel.Celestial);
    calculator = new MagicCreationCostCalculator(magicCostEvaluator, creationData, new CostAnalyzerImpl(hero));
  }

  @Test
  public void testNoSpellsLearned() {
    assertEquals(0, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointCost());
  }

  @Test
  public void calculatesCategoriesForUnfavoredSpell() {
    spells.addSpells(Collections.<Spell>singletonList(new DummySpell()));
    calculator.calculateMagicCosts();
    assertEquals(1, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointCost());
  }

  @Test
  public void calculatesCategoriesForFavoredSpell() {
    setSpellsFavored();
    spells.addSpells(Collections.<Spell>singletonList(new DummySpell()));
    calculator.calculateMagicCosts();
    assertEquals(0, calculator.getGeneralCharmPicksSpent());
    assertEquals(1, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointCost());
  }

  @Test
  public void testUnfavoredSpellsOverflowToBonus() {
    DummySpell dummySpell = new DummySpell();
    spells.addSpells(ImmutableList.<Spell>of(dummySpell, dummySpell, dummySpell, dummySpell));
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(5, calculator.getBonusPointCost());
  }

  @Test
  public void testUnfavoredSpellsOverflowToBonusAndAreReset() {
    DummySpell dummySpell = new DummySpell();
    DummySpell dummySpellToRemove = new DummySpell();
    spells.addSpells(ImmutableList.<Spell>of(dummySpell, dummySpell, dummySpell, dummySpellToRemove));
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(5, calculator.getBonusPointCost());
    spells.removeSpells(Collections.<Spell>singletonList(dummySpellToRemove), false);
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
  }

  @Test
  public void removalRemovesBonusPointCost() {
    DummySpell dummySpell = new DummySpell();
    DummySpell dummySpellToRemove = new DummySpell();
    spells.addSpells(ImmutableList.<Spell>of(dummySpell, dummySpell, dummySpell, dummySpellToRemove), false);
    spells.removeSpells(Collections.<Spell>singletonList(dummySpellToRemove), false);
    calculator.calculateMagicCosts();
    assertThat(calculator.getBonusPointCost(), is(0));
  }

  @Test
  public void testFavoredSpellsOverflowToGeneralAndBonus() {
    setSpellsFavored();
    spells.addSpells(ImmutableList.<Spell>of(new DummySpell(), new DummySpell(), new DummySpell(), new DummySpell(),
            new DummySpell(), new DummySpell()));
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(2, calculator.getFavoredCharmPicksSpent());
    assertEquals(4, calculator.getBonusPointCost());
  }

  private void setSpellsFavored() {
    charmModel.addFavoredChecker(new FavorsSpells());
  }

  private static class FavorsSpells implements FavoredChecker {
    @Override
    public boolean supportsMagic(Magic magic) {
      return magic instanceof Spell;
    }

    @Override
    public boolean isFavored(Magic magic) {
      return true;
    }
  }
}