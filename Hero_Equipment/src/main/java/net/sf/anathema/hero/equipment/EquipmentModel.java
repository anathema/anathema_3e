package net.sf.anathema.hero.equipment;

import net.sf.anathema.equipment.character.UnarmedModificationProvider;
import net.sf.anathema.equipment.database.IEquipmentTemplateProvider;
import net.sf.anathema.equipment.stats.IArmourStats;
import net.sf.anathema.hero.equipment.model.IEquipmentItemCollection;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.StatsModifierFactory;
import net.sf.anathema.hero.spiritual.model.pool.IEssencePoolModifier;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface EquipmentModel extends HeroModel, IEquipmentItemCollection, IEquipmentTemplateProvider, IEssencePoolModifier, StatsModifierFactory {

  Identifier ID = new SimpleIdentifier("Equipment");

  EquipmentHeroEvaluator getHeroEvaluator();

  void refreshItems();

  EquipmentOptionsProvider getOptionProvider();

  IArmourStats getNaturalArmor();
  
  void addUnarmedModification(UnarmedModificationProvider provider);
}