package net.sf.anathema.hero.charms.persistence;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.learn.Charms;
import net.sf.anathema.hero.charms.model.learn.LearningModel;
import net.sf.anathema.hero.charms.persistence.special.SpecialCharmListPersister;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.AbstractModelJsonPersister;
import net.sf.anathema.library.identifier.Identifier;

import static net.sf.anathema.library.message.MessageType.Error;

@SuppressWarnings("UnusedDeclaration")
public class CharmsPersister extends AbstractModelJsonPersister<CharmListPto, CharmsModel> {

  public CharmsPersister() {
    super("charms", CharmListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return CharmsModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, CharmsModel model, CharmListPto pto) {
    for (CharmPto charmPto : pto.charms) {
      learnCharm(model, charmPto, pto);
    }
  }

  private void learnCharm(CharmsModel model, CharmPto charmPto, CharmListPto pto) {
    SpecialCharmListPersister specialPersister = new SpecialCharmListPersister(model);
    try {
      Charm charm = model.getCharmById(new CharmName(charmPto.charm));
      LearningModel learningModel = model.getLearningModel();
      if (!learningModel.isLearnedOnCreation(charm)) {
        learningModel.learnCharmNoParents(charm, charmPto.isExperienceLearned, false);
      }
      specialPersister.loadSpecials(model, charm, pto, charmPto.isExperienceLearned);
    } catch (IllegalArgumentException e) {
      messaging.addPermanentMessage(Error, "CharmPersistence.NoCharmFound", charmPto.charm);
    }
  }

  @Override
  protected CharmListPto saveModelToPto(CharmsModel model) {
    CharmListPto pto = new CharmListPto();
    saveCharms(model, pto);
    saveCharmSpecials(model, pto);
    return pto;
  }

  private void saveCharms(CharmsModel model, CharmListPto pto) {
    for (Charm charm : getSortedCharmList(model)) {
      saveCharm(charm, model.getLearningModel().isLearnedWithExperience(charm), pto);
    }
  }

  private void saveCharm(Charm charm, boolean isExperienceLearned, CharmListPto pto) {
    CharmPto charmPto = new CharmPto();
    charmPto.charm = charm.getName().text;
    charmPto.tree = charm.getTreeReference().name.text;
    charmPto.category = charm.getTreeReference().category.text;
    charmPto.isExperienceLearned = isExperienceLearned;
    pto.charms.add(charmPto);
  }

  private void saveCharmSpecials(CharmsModel model, CharmListPto pto) {
    for (Charm charm : getSortedCharmList(model)) {
      saveCharmSpecials(model, charm, pto);
    }
  }

  private void saveCharmSpecials(CharmsModel charmsModel, Charm charm, CharmListPto charmListPto) {
    SpecialCharmListPersister persister = new SpecialCharmListPersister(charmsModel);
    persister.saveCharmSpecials(charmsModel, charm, charmListPto);
  }

  private Charms getSortedCharmList(CharmsModel model) {
    LearningModel learningModel = model.getLearningModel();
    Charms learnedCharms = learningModel.getCharmsLearnedEitherWay();
    return learnedCharms.applySort((Charm o1, Charm o2) -> o1.getName().compareTo(o2.getName()));
  }
}
