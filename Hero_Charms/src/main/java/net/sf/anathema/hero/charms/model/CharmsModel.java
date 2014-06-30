package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostEvaluator;
import net.sf.anathema.hero.charms.display.presenter.CharmTreeArbitrator;
import net.sf.anathema.hero.charms.model.learn.LearningCharmTree;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.learn.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.learn.MagicLearner;
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.SpecialCharmLearnArbitrator;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface CharmsModel extends HeroModel, IExtendedCharmLearnableArbitrator, CharmTreeArbitrator, CharmIdMap,
        SpecialCharmLearnArbitrator, PrintMagicProvider {

  Identifier ID = new SimpleIdentifier("Charms");

  void addLearnableListener(ChangeListener listener);

  LearningCharmTree[] getAllGroups();

  CharacterType[] getCharacterTypes(boolean includeAlienTypes);

  CharmIdMap getCharmIdMap();

  LearningCharmTree[] getCharmGroups(Identifier type);

  Charm[] getLearnedCharms(boolean experienced);

  CharmSpecialsModel getSpecialCharmConfiguration(String charmId);

  void unlearnAllAlienCharms();

  boolean isAlienCharm(Charm charm);

  CharmSpecialsModel getCharmSpecialsModel(Charm charm);

  LearningCharmTree getGroup(Charm charm);

  ISpecialCharm[] getSpecialCharms();

  void addPrintProvider(PrintMagicProvider provider);

  void addLearnProvider(MagicLearner provider);

  MagicCreationCostEvaluator getMagicCostEvaluator();

  MartialArtsLevel getStandardMartialArtsLevel();

  boolean isAlienCharmAllowed();
}