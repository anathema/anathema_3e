package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.martial.MartialArtsUtilities;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmsModel;

import java.util.Collection;

import static net.sf.anathema.charm.data.CharmAttributeList.NO_STYLE_ATTRIBUTE;
import static net.sf.anathema.charm.data.martial.MartialArtsLevel.Celestial;
import static net.sf.anathema.charm.data.martial.MartialArtsUtilities.MARTIAL_ARTS;
import static net.sf.anathema.charm.data.martial.MartialArtsUtilities.hasLevel;

public class MartialArtsLearnModelImpl implements MartialArtsLearnModel {
  private final CharmsModel charmModel;

  public MartialArtsLearnModelImpl(CharmsModel charmModel) {
    this.charmModel = charmModel;
  }

  @Override
  public boolean isAnyCelestialStyleCompleted() {
    return isAnyCelestialMartialArtsGroupCompleted(getMartialArtsGroups());
  }

  private boolean isAnyCelestialMartialArtsGroupCompleted(Collection<CharmTree> groups) {
    return groups.stream().anyMatch(group -> {
      Charm martialArtsCharm = group.getCoreCharms()[0];
      return isCelestialStyle(martialArtsCharm) && isCompleted(group);
    });
  }

  private boolean isCelestialStyle(Charm martialArtsCharm) {
    return hasLevel(Celestial, martialArtsCharm) && !martialArtsCharm.hasAttribute(NO_STYLE_ATTRIBUTE);
  }

  private boolean isCompleted(CharmTree group) {
    for (Charm charm : group.getCoreCharms()) {
      if (!charmModel.getLearningModel().isCurrentlyLearned(charm)) {
        return false;
      }
    }
    return true;
  }

  private Collection<CharmTree> getMartialArtsGroups() {
    return charmModel.getTreesFor(MartialArtsUtilities.getCategory(MARTIAL_ARTS));
  }
}