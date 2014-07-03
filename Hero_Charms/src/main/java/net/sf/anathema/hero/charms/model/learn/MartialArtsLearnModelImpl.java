package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities;

import java.util.HashSet;
import java.util.Set;

import static net.sf.anathema.charm.old.CharmAttributeList.NO_STYLE_ATTRIBUTE;
import static net.sf.anathema.charm.data.martial.MartialArtsLevel.Celestial;
import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.MARTIAL_ARTS;
import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.hasLevel;

public class MartialArtsLearnModelImpl implements MartialArtsLearnModel {
  private final CharmsModel charmModel;
  private ExperienceModel experience;

  public MartialArtsLearnModelImpl(CharmsModel charmModel, ExperienceModel experience) {
    this.charmModel = charmModel;
    this.experience = experience;
  }

  @Override
  public Charm[] getLearnedCharms() {
    return charmModel.getLearnedCharms(experience.isExperienced());
  }

  @Override
  public String[] getIncompleteCelestialMartialArtsGroups() {
    return getIncompleteCelestialMartialArtsGroups(getMartialArtsGroups());
  }

  @Override
  public String[] getCompleteCelestialMartialArtsGroups() {
    Set<String> completedGroups = new HashSet<>();
    for (LearningCharmTree group : getMartialArtsGroups()) {
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

  private String[] getIncompleteCelestialMartialArtsGroups(LearningCharmTree[] groups) {
    Set<String> uncompletedGroups = new HashSet<>();
    for (LearningCharmTree group : groups) {
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

  private boolean isAnyCelestialMartialArtsGroupCompleted(LearningCharmTree[] groups) {
    for (LearningCharmTree group : groups) {
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

  private boolean isBegun(LearningCharmTree group) {
    for (Charm charm : group.getAllCharms()) {
      if (group.isLearned(charm)) {
        return true;
      }
    }
    return false;
  }

  private boolean isCompleted(LearningCharmTree group) {
    for (Charm charm : group.getCoreCharms()) {
      if (!group.isLearned(charm)) {
        return false;
      }
    }
    return true;
  }

  private LearningCharmTree[] getMartialArtsGroups() {
    return charmModel.getCharmGroups(MartialArtsUtilities.getCategory(MARTIAL_ARTS));
  }
}