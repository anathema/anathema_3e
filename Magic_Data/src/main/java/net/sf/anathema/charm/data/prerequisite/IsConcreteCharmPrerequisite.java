package net.sf.anathema.charm.data.prerequisite;

import java.util.List;
import java.util.function.Predicate;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

public class IsConcreteCharmPrerequisite implements Predicate<CharmPrerequisite> {

  public static IsConcreteCharmPrerequisite isConcreteCharmPrerequisite() {
    return new IsConcreteCharmPrerequisite();
  }

  @Override
  public boolean test(CharmPrerequisite prerequisite) {
    IsConcrete isConcrete = new IsConcrete();
    prerequisite.process(isConcrete);
    return isConcrete.isConcrete;
  }

  public static class IsConcrete implements PrerequisiteProcessor {

    public boolean isConcrete = false;

    @Override
    public void requiresMagicAttributes(MagicAttribute attribute, int count) {
      this.isConcrete = false;
    }

    @Override
    public void requiresCharm(Charm prerequisite) {
      this.isConcrete = true;
    }

    @Override
    public void requiresCharmFromSelection(Charm[] prerequisites, int threshold) {
      this.isConcrete = true;
    }

    @Override
    public void requiresCharmsOfTraits(List<RequiredTraitType> traits, CategoryReference category, int count, int minimumEssence) {
      this.isConcrete = false;
    }

    @Override
    public void requiresCharmsOfAnyOneTrait(int threshold) {
      this.isConcrete = false;
    }

    @Override
    public void requiresMagicAttributesFromTree(TreeReference tree, MagicAttribute attribute, int count) {
      this.isConcrete = false;
    }
  }
}