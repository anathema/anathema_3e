package net.sf.anathema.hero.attributes.advance;

public interface AttributeGroupPriorityVisitor {

  void acceptPrimary();

  void acceptSecondary();

  void acceptTertiary();
}