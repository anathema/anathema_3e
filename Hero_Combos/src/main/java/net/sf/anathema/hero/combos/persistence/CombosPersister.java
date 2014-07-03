package net.sf.anathema.hero.combos.persistence;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.persistence.CharmPto;
import net.sf.anathema.hero.combos.display.presenter.Combo;
import net.sf.anathema.hero.combos.display.presenter.CombosModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.lib.util.Identifier;

import static net.sf.anathema.lib.message.MessageType.Error;

@SuppressWarnings("UnusedDeclaration")
public class CombosPersister extends AbstractModelJsonPersister<ComboListPto, CombosModel> {

  public CombosPersister() {
    super("combos", ComboListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return CombosModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, CombosModel model, ComboListPto pto) {
    CharmsModel charms = CharmsModelFetcher.fetch(hero);
    for (ComboPto comboPto : pto.combos) {
      loadCombo(charms, model, comboPto);
    }
  }

  private void loadCombo(CharmsModel charms, CombosModel model, ComboPto comboPto) {
    Combo combo = model.getEditCombo();
    combo.getName().setText(comboPto.name);
    combo.getDescription().setText(comboPto.description);
    loadCharms(charms, model, comboPto);
    model.finalizeCombo();
  }

  private void loadCharms(CharmsModel charms, CombosModel model, ComboPto comboPto) {
    for (CharmPto charm : comboPto.charms) {
      try {
        Charm comboCharm = charms.getCharmById(new CharmName(charm.charm));
        model.addCharmToCombo(comboCharm, charm.isExperienceLearned);
      } catch (IllegalArgumentException e) {
        messaging.addPermanentMessage(Error, "CharmPersistence.NoCharmFound", charm.charm);
      }
    }
  }

  @Override
  protected ComboListPto saveModelToPto(CombosModel model) {
    ComboListPto pto = new ComboListPto();
    for (Combo combo : model.getAllCombos()) {
      ComboPto comboPto = new ComboPto();
      saveCombo(combo, comboPto);
      pto.combos.add(comboPto);
    }
    return pto;
  }

  private void saveCombo(Combo combo, ComboPto pto) {
    pto.name = combo.getName().getText();
    pto.description = combo.getDescription().getText();
    saveCharms(pto, combo.getCreationCharms(), false);
    saveCharms(pto, combo.getExperiencedCharms(), true);
  }

  private void saveCharms(ComboPto pto, Charm[] charms, boolean experiencedLearned) {
    for (Charm charm : charms) {
      CharmPto charmPto = new CharmPto();
      charmPto.charm = charm.getName().text;
      charmPto.tree = charm.getTreeReference().name.text;
      charmPto.category = charm.getTreeReference().category.text;
      charmPto.isExperienceLearned = experiencedLearned;
      pto.charms.add(charmPto);
    }
  }
}
