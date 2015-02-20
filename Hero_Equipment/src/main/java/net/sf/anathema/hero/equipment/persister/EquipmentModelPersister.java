package net.sf.anathema.hero.equipment.persister;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.hero.equipment.EquipmentHeroEvaluator;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.IEquipmentStatsOption;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.AbstractModelJsonPersister;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.logging.Logger;

@SuppressWarnings("UnusedDeclaration")
public class EquipmentModelPersister extends AbstractModelJsonPersister<EquipmentListPto, EquipmentModel> {

  private final Logger logger = Logger.getLogger(EquipmentModelPersister.class);

  public EquipmentModelPersister() {
    super("equipment", EquipmentListPto.class);
  }

  @Override
  protected void loadModelFromPto(Hero hero, EquipmentModel model, EquipmentListPto pto) {
    for (EquipmentPto equipment : pto.equipments) {
      fillEquipment(model, equipment);
    }
  }

  private void fillEquipment(EquipmentModel model, EquipmentPto equipment) {
    String templateId = equipment.templateId;
    String title = equipment.customTitle;
    String description = equipment.description;
    IEquipmentItem item = model.addItem(templateId);
    item.setPersonalization(title, description);
    item.setUnprinted();
    for (EquipmentStatsPto statsPto : equipment.printStats) {
      fillStats(model, item, statsPto);
    }
  }

  private void fillStats(EquipmentModel model, IEquipmentItem item, EquipmentStatsPto statsPto) {
    String printedStatId = statsPto.id;
    item.setPrinted(printedStatId);
    EquipmentHeroEvaluator provider = model.getHeroEvaluator();
    IEquipmentStats stats = item.getStat(printedStatId);
    for (EquipmentOptionPto optionPto : statsPto.options) {
      IEquipmentStatsOption option = provider.getCharacterSpecialtyOption(optionPto.name, optionPto.type);
      model.getOptionProvider().enableStatOption(item, stats, option);
    }
  }

  @Override
  protected EquipmentListPto saveModelToPto(EquipmentModel heroModel) {
    EquipmentListPto pto = new EquipmentListPto();
    ItemToPtoTransformer transformer = new ItemToPtoTransformer(heroModel);
    for (IEquipmentItem item : heroModel.getNaturalWeapons()) {
      pto.equipments.add(transformer.createPto(item));
    }
    for (IEquipmentItem item : heroModel.getEquipmentItems()) {
      pto.equipments.add(transformer.createPto(item));
    }
    return pto;
  }

  @Override
  public Identifier getModelId() {
    return EquipmentModel.ID;
  }
}