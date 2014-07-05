package net.sf.anathema.hero.specialties.advance.creation;

import net.sf.anathema.hero.traits.model.TraitListModel;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecialtyCalculator {

  private final TraitListModel abilityModel;
  private final int specialtyPoints;

  public SpecialtyCalculator(TraitListModel abilityModel, int specialtyPoints) {
    this.abilityModel = abilityModel;
    this.specialtyPoints = specialtyPoints;
  }

  public int getSpecialtyPointsSpent(IGenericSpecialty[] specialties) {
    return Math.min(specialtyPoints, specialties.length);
  }

  public int getSpecialtyCosts(IGenericSpecialty[] specialties) {
    List<IGenericSpecialty> favoredSpecialties = getFavoredSpecialties(Arrays.asList(specialties));
    List<IGenericSpecialty> unfavoredSpecialties = new ArrayList<>(Arrays.asList(specialties));
    unfavoredSpecialties.removeAll(favoredSpecialties);
    int favoredCount = favoredSpecialties.size();
    int unfavoredCount = unfavoredSpecialties.size();
    unfavoredCount -= specialtyPoints;
    if (unfavoredCount < 0) {
      favoredCount = Math.max(favoredCount + unfavoredCount, 0);
      unfavoredCount = 0;
    }
    return unfavoredCount + (int) Math.ceil(favoredCount * 0.5);
  }

  private List<IGenericSpecialty> getFavoredSpecialties(List<IGenericSpecialty> specialties) {
    List<IGenericSpecialty> cheapenedSpecialties = new ArrayList<>();
    for (IGenericSpecialty specialty : specialties) {
      TraitType type = specialty.getBasicTrait().getType();
      if (abilityModel.getState(type).isCheapened()) {
        cheapenedSpecialties.add(specialty);
      }
    }
    return cheapenedSpecialties;
  }
}