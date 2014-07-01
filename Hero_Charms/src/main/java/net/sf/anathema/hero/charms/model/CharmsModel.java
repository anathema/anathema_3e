package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostEvaluator;
import net.sf.anathema.hero.charms.display.presenter.CharmTreeArbitrator;
import net.sf.anathema.hero.charms.model.learn.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.learn.LearningCharmTree;
import net.sf.anathema.hero.charms.model.learn.MagicLearner;
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.SpecialCharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.List;

public interface CharmsModel extends HeroModel, IExtendedCharmLearnableArbitrator, CharmTreeArbitrator, CharmIdMap,
        SpecialCharmLearnArbitrator, PrintMagicProvider {

  Identifier ID = new SimpleIdentifier("Charms");

  void addPrintProvider(PrintMagicProvider provider);

  void addLearnableListener(ChangeListener listener);

  void addLearnProvider(MagicLearner provider);

  List<CategoryReference> getValidCategoriesForHero();

  CharmIdMap getCharmIdMap();

  LearningCharmTree getGroup(Charm charm);

  LearningCharmTree[] getAllGroups();

  LearningCharmTree[] getCharmGroups(CategoryReference type);

  Charm[] getLearnedCharms(boolean experienced);

  void forgetAllAlienCharms();

  boolean isAlienCharm(Charm charm);

  CharmSpecialsModel getCharmSpecialsModel(Charm charm);

  ISpecialCharm[] getSpecialCharms();

  MartialArtsLevel getStandardMartialArtsLevel();

  MagicCreationCostEvaluator getMagicCostEvaluator();

  boolean isAlienCharmAllowed();
}