package net.sf.anathema.hero.merits.advance.calculator;

import net.sf.anathema.hero.merits.advance.MeritCreationData;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.points.model.BonusPointCalculator;

import java.util.List;

public class MeritsBonusPointCalculator implements BonusPointCalculator {
  private MeritsModel model;
  private MeritCreationData creation;

  public MeritsBonusPointCalculator(MeritsModel model, MeritCreationData creation) {
    this.model = model;
    this.creation = creation;
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }

  @Override
  public void recalculate() {
    // TODO Auto-generated method stub
  }

  @Override
  public int getBonusPointCost() {
    List<Merit> meritsNotYetCovered = findMeritsNotLearnedForFree();
    int pointsNotCovered = countPointsNotCovered(meritsNotYetCovered);
    int pointsToPayFor = Math.max(0, pointsNotCovered - creation.getFreebiePoints());
    return creation.getBonusPointCost() * pointsToPayFor;
  }

  public int getPointsCoveredByFreebies() {
    List<Merit> meritsNotYetCovered = findMeritsNotLearnedForFree();
    int pointsNotCovered = countPointsNotCovered(meritsNotYetCovered);
    return Math.min(creation.getFreebiePoints(), pointsNotCovered);
  }

  private List<Merit> findMeritsNotLearnedForFree() {
    FreeMerits freeMerits = creation.createFreeMerits();
    List<Merit> possessedMerits = model.getEntries();
    return freeMerits.cover(possessedMerits);
  }

  private int countPointsNotCovered(List<Merit> meritsNotYetCovered) {
    int totalMeritDots = 0;
    for (Merit merit : meritsNotYetCovered) {
      totalMeritDots += merit.getCurrentValue();
    }
    return totalMeritDots;
  }
}