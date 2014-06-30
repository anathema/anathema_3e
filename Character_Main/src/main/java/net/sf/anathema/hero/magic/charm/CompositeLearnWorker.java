package net.sf.anathema.hero.magic.charm;

import net.sf.anathema.hero.magic.basic.attribute.MagicAttribute;

import java.util.HashSet;
import java.util.Set;

public class CompositeLearnWorker implements ICharmLearnWorker {

  private final ICharmLearnArbitrator learnArbitrator;
  private final Set<Charm> forgottenCharm = new HashSet<>();

  public CompositeLearnWorker(ICharmLearnArbitrator learnArbitrator) {
    this.learnArbitrator = learnArbitrator;
  }

  @Override
  public boolean isLearned(Charm charm) {
    return learnArbitrator.isLearned(charm) && !forgottenCharm.contains(charm);
  }
  
  @Override
  public boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute,
  		int threshold) {
  	return learnArbitrator.hasLearnedThresholdCharmsWithKeyword(attribute, threshold);
  }

  @Override
  public void forget(Charm charm) {
    forgottenCharm.add(charm);
  }

  public Set<Charm> getForgottenCharms() {
    return new HashSet<>(forgottenCharm);
  }
}