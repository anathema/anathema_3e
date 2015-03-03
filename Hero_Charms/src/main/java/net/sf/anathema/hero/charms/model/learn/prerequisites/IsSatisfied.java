package net.sf.anathema.hero.charms.model.learn.prerequisites;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteProcessor;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.model.learn.CharmLearnArbitrator;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import com.google.common.collect.Lists;

import java.util.List;

public class IsSatisfied implements PrerequisiteProcessor {

  public static boolean isSatisfied(CharmPrerequisite prerequisite, CharmLearnArbitrator arbitrator) {
    IsSatisfied satisfied = new IsSatisfied(arbitrator);
    prerequisite.process(satisfied);
    return satisfied.satisfied;
  }

  private CharmLearnArbitrator arbitrator;
  public boolean satisfied = true;

  public IsSatisfied(CharmLearnArbitrator learnArbitrator) {
    this.arbitrator = learnArbitrator;
  }

  @Override
  public void requiresMagicAttributes(MagicAttribute attribute, int count) {
    this.satisfied = arbitrator.hasLearnedThresholdCharmsWithKeyword(attribute, count);
  }

  @Override
  public void requiresMagicAttributesFromTree(TreeReference tree,
                                              MagicAttribute attribute, int count) {
    this.satisfied = arbitrator.hasLearnedThresholdCharmsWithKeywordFromTree(tree, attribute, count);
  }

  @Override
  public void requiresCharm(Charm prerequisite) {
    this.satisfied = arbitrator.isLearned(prerequisite);
  }

  @Override
  public void requiresCharmFromSelection(Charm[] prerequisites, int count) {
    int known = 0;
    for (Charm charm : prerequisites) {
      if (arbitrator.isLearned(charm)) {
        known++;
      }
      if (known >= count) {
        this.satisfied = true;
        return;
      }
    }
    this.satisfied = false;
  }

  @Override
  public void requiresCharmsOfTraits(List<RequiredTraitType> traits, CategoryReference category, int count,
                                     int minimumEssence) {
    List<TraitType> traitTypes = Lists.transform(traits, trait -> new TraitTypeFinder().getTrait(trait.type));
    this.satisfied = arbitrator.hasLearnedThresholdCharmsOfTrait(traitTypes, category, count, minimumEssence);
  }

  @Override
  public void requiresCharmsOfAnyOneTrait(int count) {
    this.satisfied = arbitrator.hasLearnedThresholdCharmsOfAnyOneTrait(count);
  }
}