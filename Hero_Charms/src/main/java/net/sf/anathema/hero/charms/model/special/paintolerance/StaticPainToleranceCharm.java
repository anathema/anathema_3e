package net.sf.anathema.hero.charms.model.special.paintolerance;

import com.google.common.base.Preconditions;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.parser.template.special.PainTolerance;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;
import net.sf.anathema.hero.charms.model.special.multilearn.StaticMultiLearnableCharm;

import java.util.ArrayList;
import java.util.List;

public class StaticPainToleranceCharm extends StaticMultiLearnableCharm implements IPainToleranceCharm {

  private final List<Integer> painToleranceLevels;

  public StaticPainToleranceCharm(CharmName charmId, PainTolerance dto) {
    super(charmId, dto.learnCount);
    Preconditions.checkArgument(dto.learnCount == dto.levels.size(), "Number of pain tolerance levels must equal learn count.");
    this.painToleranceLevels = new ArrayList<>(dto.levels);
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    super.accept(visitor);
    visitor.visitPainToleranceCharm(this);
  }

  @Override
  public int getPainToleranceLevel(int learnCount) {
    if (learnCount <= 0) {
      return 0;
    }
    return painToleranceLevels.get(learnCount - 1);
  }
}