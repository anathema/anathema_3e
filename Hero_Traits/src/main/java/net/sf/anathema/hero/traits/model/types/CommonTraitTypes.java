package net.sf.anathema.hero.traits.model.types;

import com.google.common.collect.Lists;
import net.sf.anathema.hero.traits.model.DefaultTraitType;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.List;

public interface CommonTraitTypes {
  //Spiritual
  DefaultTraitType Willpower = new DefaultTraitType("Willpower");
  DefaultTraitType Essence = new DefaultTraitType("Essence");
  List<TraitType> SpiritualTraits = Lists.newArrayList(Willpower, Essence);
  
  //Attributes
  DefaultTraitType Strength = new DefaultTraitType("Strength");
  DefaultTraitType Dexterity = new DefaultTraitType("Dexterity");
  DefaultTraitType Stamina = new DefaultTraitType("Stamina");
  DefaultTraitType Charisma = new DefaultTraitType("Charisma");
  DefaultTraitType Manipulation = new DefaultTraitType("Manipulation");
  DefaultTraitType Appearance = new DefaultTraitType("Appearance");
  DefaultTraitType Perception = new DefaultTraitType("Perception");
  DefaultTraitType Intelligence = new DefaultTraitType("Intelligence");
  DefaultTraitType Wits = new DefaultTraitType("Wits");
  List<TraitType> Attributes = Lists.newArrayList(Strength, Dexterity, Stamina, Charisma, Manipulation, Appearance, Perception, Intelligence, Wits);

  //Abilities
  DefaultTraitType Archery = new DefaultTraitType("Archery");
  DefaultTraitType MartialArts = new DefaultTraitType("MartialArts");
  DefaultTraitType Melee = new DefaultTraitType("Melee");
  DefaultTraitType Integrity = new DefaultTraitType("Integrity");
  DefaultTraitType Performance = new DefaultTraitType("Performance");
  DefaultTraitType Presence = new DefaultTraitType("Presence");
  DefaultTraitType Resistance = new DefaultTraitType("Resistance");
  DefaultTraitType Investigation = new DefaultTraitType("Investigation");
  DefaultTraitType Occult = new DefaultTraitType("Occult");
  DefaultTraitType Athletics = new DefaultTraitType("Athletics");
  DefaultTraitType Awareness = new DefaultTraitType("Awareness");
  DefaultTraitType Dodge = new DefaultTraitType("Dodge");
}