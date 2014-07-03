package net.sf.anathema.hero.charms.display.prerequisites;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.prerequisite.AttributeKnownCharmPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.DirectGroupCharmPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.PrerequisiteVisitor;
import net.sf.anathema.hero.magic.charm.prerequisite.SimpleCharmPrerequisite;

import static net.sf.anathema.hero.magic.charm.prerequisite.AcceptVisitor.acceptVisitor;

public class IsTreeRoot implements PrerequisiteVisitor {

  public static boolean isTreeRoot(Charm charm) {
    IsTreeRoot visitor = new IsTreeRoot();
    charm.getPrerequisites().forEachCharmPrerequisite(acceptVisitor(visitor));
    return visitor.isRoot;
  }

  public boolean isRoot = true;

  @Override
  public void visit(AttributeKnownCharmPrerequisite prerequisite) {
    isRoot = false;
  }

  @Override
  public void visit(DirectGroupCharmPrerequisite prerequisite) {
    isRoot = false;
  }

  @Override
  public void visit(SimpleCharmPrerequisite prerequisite) {
    isRoot = false;
  }
}
