package net.sf.anathema.hero.charms.model.special.subeffects;

import com.google.common.base.Objects;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.learn.CharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.special.CharmSpecialist;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;
import net.sf.anathema.lib.data.Condition;

import java.util.ArrayList;
import java.util.List;

public class MultipleEffectCharm implements IMultipleEffectCharm {

  private final CharmName charmId;
  protected final String[] effectIds;

  public MultipleEffectCharm(CharmName charmId, String[] effectIds) {
    this.charmId = charmId;
    this.effectIds = effectIds;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitMultipleEffectCharm(this);
  }

  @Override
  public CharmName getCharmName() {
    return charmId;
  }

  @Override
  public SubEffects buildSubEffects(CharmSpecialist specialist, CharmLearnableArbitrator arbitrator, Charm charm) {
    List<SubEffect> effectList = new ArrayList<>();
    for (String id : effectIds) {
      effectList.add(new SubEffectImpl(id, specialist.getExperience(), buildLearnCondition(arbitrator, charm)));
    }
    return new ArraySubEffects(effectList.toArray(new SubEffect[effectList.size()]));
  }

  private Condition buildLearnCondition(CharmLearnableArbitrator arbitrator, Charm charm) {
    return new ArbitratorLearnCondition(arbitrator, charm);
  }

  public String toString() {
    StringBuilder list = new StringBuilder();
    for (String effect : effectIds) {
      boolean isLastEffect = Objects.equal(effect, effectIds[effectIds.length - 1]);
      list.append(effect);
      list.append(isLastEffect ? "" : ",");
    }
    return "[" + getCharmName() + ";" + list + "]";
  }
}