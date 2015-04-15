package net.sf.anathema.hero.abilities;

import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.hero.traits.model.group.GroupName;
import net.sf.anathema.hero.traits.model.group.TraitTypeGroupFactory;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import org.junit.Test;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Archery;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Investigation;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Melee;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TraitTypeGroupFactoryTest {

  private TraitTypeGroupFactory factory = new TraitTypeGroupFactory();

  @Test
  public void testOneGroup() throws Exception {
    GroupName war = new GroupName(("War"));
    GroupedTraitType[] abilityTypes = new GroupedTraitType[]{
            new GroupedTraitType(Archery, war),
            new GroupedTraitType(Melee, war)};
    IdentifiedTraitTypeList[] typeGroups = factory.createTraitGroups(abilityTypes);
    assertEquals(1, typeGroups.length);
    assertThat(typeGroups[0].getAll(), contains(Archery, Melee));
    assertEquals(war, typeGroups[0].getListId());
  }

  @Test
  public void testDifferentGroups() throws Exception {
    GroupName war = new GroupName("War");
    GroupName life = new GroupName("Life");
    GroupedTraitType[] abilityTypes = new GroupedTraitType[]{
            new GroupedTraitType(Archery, war),
            new GroupedTraitType(Investigation, life)
    };
    IdentifiedTraitTypeList[] typeGroups = factory.createTraitGroups(abilityTypes);
    assertEquals(2, typeGroups.length);
    assertThat(typeGroups[0].getAll(), contains(Archery));
    assertEquals(war, typeGroups[0].getListId());
    assertThat(typeGroups[1].getAll(), contains(Investigation));
    assertEquals(life, typeGroups[1].getListId());
  }
}