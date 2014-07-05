package net.sf.anathema.hero.abilities;

import net.sf.anathema.hero.abilities.model.AbilityTypeGroupFactory;
import net.sf.anathema.hero.concept.model.concept.CasteCollection;
import net.sf.anathema.hero.dummy.models.NullCasteCollection;
import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import org.junit.Test;

import static net.sf.anathema.hero.abilities.model.AbilityGroupType.Life;
import static net.sf.anathema.hero.abilities.model.AbilityGroupType.War;
import static net.sf.anathema.hero.traits.model.types.AbilityType.Archery;
import static net.sf.anathema.hero.traits.model.types.AbilityType.Medicine;
import static net.sf.anathema.hero.traits.model.types.AbilityType.Melee;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AbilityTraitTypeGroupFactoryTest {

  private AbilityTypeGroupFactory factory = new AbilityTypeGroupFactory();

  @Test
  public void testOneGroup() throws Exception {
    CasteCollection casteCollection = new NullCasteCollection();
    GroupedTraitType[] abilityTypes = new GroupedTraitType[]{
            new GroupedTraitType(Archery, "War"),
            new GroupedTraitType(Melee, "War")
    };
    IdentifiedTraitTypeList[] typeGroups = factory.createTraitGroups(casteCollection, abilityTypes);
    assertEquals(1, typeGroups.length);
    assertThat(typeGroups[0].getAll(), contains(Archery, Melee));
    assertEquals(War, typeGroups[0].getListId());
  }

  @Test
  public void testDifferentGroups() throws Exception {
    CasteCollection casteCollection = new NullCasteCollection();
    GroupedTraitType[] abilityTypes = new GroupedTraitType[]{
            new GroupedTraitType(Archery, "War"),
            new GroupedTraitType(Medicine, "Life")
    };
    IdentifiedTraitTypeList[] typeGroups = factory.createTraitGroups(casteCollection, abilityTypes);
    assertEquals(2, typeGroups.length);
    assertThat(typeGroups[0].getAll(), contains(Archery));
    assertEquals(War, typeGroups[0].getListId());
    assertThat(typeGroups[1].getAll(), contains(Medicine));
    assertEquals(Life, typeGroups[1].getListId());
  }
}