package net.sf.anathema.charm.data.prerequisite;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import java.util.List;
import java.util.function.Predicate;

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

  private static class IsConcrete implements PrerequisiteProcessor {

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
	public void requiresCharmsOfTraits(List<RequiredTraitType> traits,
			int count, int minimumEssence) {
	  this.isConcrete = false;
	}
  }
}
