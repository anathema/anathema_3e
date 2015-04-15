package net.sf.anathema.hero.traits.model.types;

import com.google.common.collect.Lists;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.List;

public interface CommonTraitTypes {
  //Spiritual
  SpiritualTraitType Willpower = new SpiritualTraitType("Willpower");
  SpiritualTraitType Essence = new SpiritualTraitType("Essence");
  List<TraitType> SpiritualTraits = Lists.newArrayList(Willpower, Essence);
  
  //Attributes
  AttributeTraitType Strength = new AttributeTraitType("Strength");
  AttributeTraitType Dexterity = new AttributeTraitType("Dexterity");
  AttributeTraitType Stamina = new AttributeTraitType("Stamina");
  AttributeTraitType Charisma = new AttributeTraitType("Charisma");
  AttributeTraitType Manipulation = new AttributeTraitType("Manipulation");
  AttributeTraitType Appearance = new AttributeTraitType("Appearance");
  AttributeTraitType Perception = new AttributeTraitType("Perception");
  AttributeTraitType Intelligence = new AttributeTraitType("Intelligence");
  AttributeTraitType Wits = new AttributeTraitType("Wits");
  List<TraitType> Attributes = Lists.newArrayList(Strength, Dexterity, Stamina, Charisma, Manipulation, Appearance, Perception, Intelligence, Wits);
}