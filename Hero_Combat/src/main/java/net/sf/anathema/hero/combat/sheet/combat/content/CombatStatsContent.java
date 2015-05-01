package net.sf.anathema.hero.combat.sheet.combat.content;

import net.sf.anathema.hero.combat.model.CharacterUtilities;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.content.stats.HeroStatsModifiers;
import net.sf.anathema.hero.sheet.pdf.content.stats.StatsModifiers;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.library.resources.Resources;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Awareness;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Dodge;

public class CombatStatsContent extends AbstractSubBoxContent {

  public static final int SPECIALTY_INCREMENT = 1;
  private final HeroStatsModifiers modifiers;
  private final Hero hero;

  protected CombatStatsContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
    modifiers = StatsModifiers.allStatsModifiers(hero);
  }

  public String getJoinLabel() {
    return getString("Sheet.Combat.JoinBattle");
  }

  public String getEvasionLabel() {
    return getString("Sheet.Combat.Evasion");
  }

  public String getJoinBattleSpecialtyLabel() {
    return getString("Sheet.Combat.IncreasesWithSpecialty", Awareness);
  }

  public String getDodgeSpecialtyLabel() {
    return getString("Sheet.Combat.IncreasesWithSpecialty", Dodge);
  }

  public int getJoinBattle() {
    return CharacterUtilities.getJoinBattle(getTraits());
  }

  public int getJoinBattleWithSpecialty() {
    return CharacterUtilities.getJoinBattleWithSpecialty(getTraits(), SPECIALTY_INCREMENT);
  }

  public int getDodgeDv() {
    return CharacterUtilities.getEvasion(getCharacterType(), getTraits(), modifiers);
  }

  public int getEvasionWithSpecialty() {
    return CharacterUtilities.getDodgeDvWithSpecialty(getCharacterType(), getTraits(), modifiers, SPECIALTY_INCREMENT);
  }

  public String[] getOrderOfAttack() {
    return new String[]{getString("Sheet.Combat.AttackList.DeclareAttack"), getString(
      "Sheet.Combat.AttackList.DeclareDefence"), getString("Sheet.Combat.AttackList.AttackRoll"), getString(
      "Sheet.Combat.AttackList.AttackReroll"), getString("Sheet.Combat.AttackList.SubstractPenalties"), getString(
      "Sheet.Combat.AttackList.DefenseReroll"), getString("Sheet.Combat.AttackList.CalculateRawDamage"), getString(
      "Sheet.Combat.AttackList.RollDamage"), getString("Sheet.Combat.AttackList.Counterattack"), getString(
      "Sheet.Combat.AttackList.ApplyDamage")

    };
  }

  public QualifiedText[] getRulesOfInterest() {
    return new QualifiedText[]{new QualifiedText(getString("Sheet.Combat.Prone.Header") + "\n",
      TextType.Normal), new QualifiedText(getString("Sheet.Combat.Prone.Comment") + "\n\n",
      TextType.Comment), new QualifiedText(getString("Sheet.Combat.Flurry.Header") + "\n",
      TextType.Normal), new QualifiedText(getString("Sheet.Combat.Flurry.Comment"), TextType.Comment)

    };
  }

  public String getAttackHeader() {
    return getString("Sheet.Combat.OrderAttackEvents");
  }

  public String getAttackComment() {
    return getString("Sheet.Combat.Comment.Rules");
  }

  public MovementAction[] getMovementActions() {
    return new MovementAction[]{
      createMovementAction("Combat.MovementAction.Move", "Combat.ActionType.Reflexive"),
      createMovementAction("Combat.MovementAction.Rush", "Combat.ActionType.Combat"),
      createMovementAction("Combat.MovementAction.Disengage", "Combat.ActionType.Combat"),
      createMovementAction("Combat.MovementAction.RiseFromProne", "Combat.ActionType.Combat"),
      createMovementAction("Combat.MovementAction.TakeCover", "Combat.ActionType.Combat"),
      createMovementAction("Combat.MovementAction.Withdraw", "Combat.ActionType.Combat")
    };
  }

  public String getMovementActionHeader() {
    return getResources().getString("Sheet.Combat.MovementActions.Header");
  }

  private MovementAction createMovementAction(String nameKey, String typeKey) {
    String name = getResources().getString(nameKey);
    String type = getResources().getString(typeKey);
    return new MovementAction(name, type);
  }

  public String getCombatActionHeader() {
    return getResources().getString("Sheet.Combat.CombatActions.Header");
  }

  public String[] getCombatActions() {
    return new String[]{
      getString("Combat.CombatAction.Attack"),
      getString("Combat.CombatAction.Aim"),
      getString("Combat.CombatAction.DefendOther"),
      getString("Combat.CombatAction.DrawReadyWeapon"),
      getString("Combat.CombatAction.FullDefense"),
      getString("Combat.CombatAction.MiscellaneousAction")
    };
  }

  @Override
  public String getHeaderKey() {
    return "Combat";
  }

  @Override
  public boolean hasContent() {
    return true;
  }

  private HeroType getCharacterType() {
    return hero.getSplat().getTemplateType().getHeroType();
  }

  private TraitMap getTraits() {
    return TraitModelFetcher.fetch(hero);
  }
}
