package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.magic.attribute.MagicAttribute;
import net.sf.anathema.hero.magic.charm.Charm;

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
  }
}
