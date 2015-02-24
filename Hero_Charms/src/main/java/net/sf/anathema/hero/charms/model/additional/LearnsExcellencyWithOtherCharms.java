package net.sf.anathema.hero.charms.model.additional;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.learn.CharmLearnAdapter;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")
//Auto-instantiated by Charms Model
public class LearnsExcellencyWithOtherCharms extends ExcellencyAdditionalRules {

  private final static String id = "LearnsExcellencyWithOtherCharm";

  private final CharmsModel charms;

  public LearnsExcellencyWithOtherCharms(CharmsModel charms, Hero hero) {
    super(hero.getSplat().getTemplateType().getHeroType());
    this.charms = charms;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void initialize() {
    charms.addCharmLearnListener(new ExcellencyMonitor());
  }

  private class ExcellencyMonitor extends CharmLearnAdapter {

    private boolean hasOtherCharmsOfTrait(String trait) {
      TraitType traitType = new TraitTypeFinder().getTrait(trait);
      List<TraitType> traitTypes = new ArrayList<>();
      traitTypes.add(traitType);
      return charms.hasLearnedThresholdCharmsOfTrait(traitTypes, null, 1, 1);
    }

    @Override
    public void charmLearned(Charm charm) {
      String primaryTrait = charm.getPrerequisites().getPrimaryTraitType().type;
      CharmName excellencyName = new CharmName(getStringForExcellency(primaryTrait));
      try {
        Charm excellency = charms.getCharmById(excellencyName);
        if (!charms.isLearned(excellency) && hasOtherCharmsOfTrait(primaryTrait)) {
          charms.getLearningModel().learnCharm(excellency, false);
        }
      } catch (IllegalArgumentException e) {
        // corresponding excellency does not exist for this character
      }
    }

    @Override
    public void charmForgotten(Charm charm) {
      String primaryTrait = charm.getPrerequisites().getPrimaryTraitType().type;
      CharmName excellencyName = new CharmName(getStringForExcellency(primaryTrait));
      try {
        Charm excellency = charms.getCharmById(excellencyName);
        if (charms.isLearned(excellency) && !hasOtherCharmsOfTrait(primaryTrait)) {
          charms.getLearningModel().forgetCharm(excellency, false);
        }
      } catch (IllegalArgumentException e) {
        // corresponding excellency does not exist for this character
      }
    }
  }
}