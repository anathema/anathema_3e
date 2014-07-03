package net.sf.anathema.hero.charms.model.special.oxbody;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;
import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.LinkedHashMap;
import java.util.Map;

public class OxBodyTechniqueCharm implements IOxBodyTechniqueCharm {

  private final TraitType[] traitTypes;
  private final LinkedHashMap<String, HealthLevelType[]> healthLevels;
  private final CharmName charmId;

  public OxBodyTechniqueCharm(CharmName charmId, TraitType traitType, LinkedHashMap<String, HealthLevelType[]> healthLevels) {
    this(charmId, new TraitType[]{traitType}, healthLevels);
  }

  public OxBodyTechniqueCharm(CharmName charmId, TraitType[] traitTypes, LinkedHashMap<String, HealthLevelType[]> healthLevels) {
    this.traitTypes = traitTypes;
    this.healthLevels = healthLevels;
    this.charmId = charmId;
  }

  @Override
  public TraitType[] getRelevantTraits() {
    return traitTypes;
  }

  @Override
  public Map<String, HealthLevelType[]> getHealthLevels() {
    return new LinkedHashMap<>(healthLevels);
  }

  @Override
  public CharmName getCharmName() {
    return charmId;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitOxBodyTechnique(this);
  }
}