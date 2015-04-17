package net.sf.anathema.hero.abilities.sheet.content;

import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.TraitTypeList;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.traits.sheet.content.FavorableTraitContent;
import net.sf.anathema.library.resources.Resources;

public class AbilitiesContent extends FavorableTraitContent {

  private Hero hero;

  public AbilitiesContent(Hero hero, Resources resources) {
    super(AbilitiesModelFetcher.fetch(hero), resources);
    this.hero = hero;
  }

  @Override
  public TraitTypeList getMarkedTraitTypes() {
    TraitTypeList typeList = new TraitTypeList();
    typeList.add("Athletics");
    typeList.add("Dodge");
    typeList.add("Larceny");
    typeList.add("Ride");
    typeList.add("Stealth");
    return typeList;
  }

  @Override
  public IdentifiedTraitTypeList[] getIdentifiedTraitTypeGroups() {
    return AbilitiesModelFetcher.fetch(hero).getGroups();
  }

  @Override
  public TraitMap getTraitMap() {
    return TraitModelFetcher.fetch(hero);
  }

  @Override
  public String getGroupNamePrefix() {
    return "AbilityGroup.";
  }

  @Override
  public String getTraitTypePrefix() {
    return "";
  }

  @Override
  public String getMarkerCommentKey() {
    return "Sheet.Comment.AbilityMobility";
  }

  @Override
  public String getHeaderKey() {
    return "Abilities";
  }
}
