package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.martial.MartialArtsUtilities;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmsModel;

import java.util.HashSet;
import java.util.Set;

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
  public Charm[] getLearnedCharms() {
    return charmModel.getLearnedCharms();
  }

  @Override
  public String[] getIncompleteCelestialMartialArtsGroups() {
    return getIncompleteCelestialMartialArtsGroups(getMartialArtsGroups());
  }

  @Override
  public String[] getCompleteCelestialMartialArtsGroups() {
    Set<String> completedGroups = new HashSet<>();
    for (CharmTree group : getMartialArtsGroups()) {
      Charm martialArtsCharm = group.getCoreCharms()[0];
      if (isCelestialStyle(martialArtsCharm) && isCompleted(group)) {
        completedGroups.add(group.getId());
      }
    }
    return completedGroups.toArray(new String[completedGroups.size()]);
  }

  @Override
  public boolean isAnyCelestialStyleCompleted() {
    return isAnyCelestialMartialArtsGroupCompleted(getMartialArtsGroups());
  }

  private String[] getIncompleteCelestialMartialArtsGroups(CharmTree[] groups) {
    Set<String> uncompletedGroups = new HashSet<>();
    for (CharmTree group : groups) {
      Charm martialArtsCharm = group.getCoreCharms()[0];
      if (!isCelestialStyle(martialArtsCharm) || isCompleted(group)) {
        continue;
      }
      if (isBegun(group)) {
        uncompletedGroups.add(group.getId());
      }
    }
    return uncompletedGroups.toArray(new String[uncompletedGroups.size()]);
  }

  private boolean isAnyCelestialMartialArtsGroupCompleted(CharmTree[] groups) {
    for (CharmTree group : groups) {
      Charm martialArtsCharm = group.getCoreCharms()[0];
      if (isCelestialStyle(martialArtsCharm) && isCompleted(group)) {
        return true;
      }
    }
    return false;
  }

  private boolean isCelestialStyle(Charm martialArtsCharm) {
    return hasLevel(Celestial, martialArtsCharm) && !martialArtsCharm.hasAttribute(NO_STYLE_ATTRIBUTE);
  }

  private boolean isBegun(CharmTree group) {
    for (Charm charm : group.getAllCharms()) {
      if (charmModel.getLearnModel().isLearned(charm)) {
        return true;
      }
    }
    return false;
  }

  private boolean isCompleted(CharmTree group) {
    for (Charm charm : group.getCoreCharms()) {
      if (!charmModel.getLearnModel().isLearned(charm)) {
        return false;
      }
    }
    return true;
  }

  private CharmTree[] getMartialArtsGroups() {
    return charmModel.getTreesFor(MartialArtsUtilities.getCategory(MARTIAL_ARTS));
  }
}