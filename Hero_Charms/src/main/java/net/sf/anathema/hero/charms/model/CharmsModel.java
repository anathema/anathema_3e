package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.martial.MartialArtsLevel;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostEvaluator;
import net.sf.anathema.hero.charms.model.favored.CheapenedChecker;
import net.sf.anathema.hero.charms.model.learn.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.learn.LearningModel;
import net.sf.anathema.hero.charms.model.learn.MagicLearner;
import net.sf.anathema.hero.charms.model.options.CharmOptions;
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.hero.charms.model.special.SpecialCharmLearnArbitrator;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.magic.data.Magic;

public interface CharmsModel extends HeroModel, IExtendedCharmLearnableArbitrator, CharmMap,
        SpecialCharmLearnArbitrator, PrintMagicProvider {

  void addCheapenedChecker(CheapenedChecker cheapenedChecker);

  Identifier ID = new SimpleIdentifier("Charms");

  void addPrintProvider(PrintMagicProvider provider);

  void addLearnableListener(ChangeListener listener);

  void addLearnProvider(MagicLearner provider);

  LearningModel getLearnModel();

  CharmTree[] getTreesFor(CategoryReference type);

  CharmTree[] getAllTrees();

  Charm[] getLearnedCharms(boolean experienced);

  void forgetAllAlienCharms();

  CharmSpecialsModel getCharmSpecialsModel(Charm charm);

  MartialArtsLevel getStandardMartialArtsLevel();

  MagicCreationCostEvaluator getMagicCostEvaluator();

  CharmOptions getOptions();

  boolean isAlienCharm(Charm charm);

  boolean isMagicCheapened(Magic magic);
}