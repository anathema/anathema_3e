package net.sf.anathema.hero.magic.dummy;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnListener;
import net.sf.anathema.hero.charms.model.learn.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DummyLearnableArbitrator implements IExtendedCharmLearnableArbitrator {

  private final List<String> learnableCharmIds = new ArrayList<>();

  public DummyLearnableArbitrator(String... learnableCharmIds) {
    Collections.addAll(this.learnableCharmIds, learnableCharmIds);
  }

  @Override
  public boolean isLearnable(Charm charm) {
    return learnableCharmIds.contains(charm.getName().text);
  }
  
  @Override
  public boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute,
  		int threshold) {
  	return false;
  }
  
  @Override
	public boolean hasLearnedThresholdCharmsWithKeywordFromTree(
			TreeReference tree, MagicAttribute attribute, int threshold) {
		return false;
	}
  
  @Override
	public boolean hasLearnedThresholdCharmsOfTrait(List<TraitType> traits, CategoryReference category,
			int threshold, int minimumEssence) {
		return false;
	}
  
  @Override
	public boolean hasLearnedThresholdCharmsOfAnyOneTrait(int threshold) {
		return false;
	}

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    // Nothing to do
  }

  @Override
  public boolean isLearned(Charm charm) {
    return false;
  }
}