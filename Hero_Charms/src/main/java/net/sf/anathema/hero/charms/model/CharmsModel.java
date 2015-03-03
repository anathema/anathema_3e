package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.model.favored.CheapenedChecker;
import net.sf.anathema.hero.charms.model.learn.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.learn.LearningModel;
import net.sf.anathema.hero.charms.model.options.CharmOptions;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearningModel;
import net.sf.anathema.hero.charms.model.special.SpecialCharmLearnArbitrator;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.magic.data.Magic;

import java.util.Collection;

public interface CharmsModel extends HeroModel, IExtendedCharmLearnableArbitrator, CharmMap,
        SpecialCharmLearnArbitrator, PrintMagicProvider {

  Identifier ID = new SimpleIdentifier("Charms");

  void addCheapenedChecker(CheapenedChecker cheapenedChecker);

  void addPrintProvider(PrintMagicProvider provider);

  void addLearnableListener(ChangeListener listener);

  CharmSpecialLearningModel getCharmSpecialLearningModel(Charm charm);

  LearningModel getLearningModel();

  void forgetAllAlienCharms();

  Collection<CharmTree> getTreesFor(CategoryReference type);

  Collection<CharmTree> getAllTrees();

  CharmOptions getOptions();

  boolean isAlienCharm(Charm charm);

  boolean isMagicCheapened(Magic magic);
}