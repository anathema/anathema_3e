package net.sf.anathema.library.legality;

public interface IValueLegalityStateVisitor {

  void visitLow(ValueLegalityState visitedState);

  void visitLowered(ValueLegalityState state);

  void visitOkay(ValueLegalityState visitedState);

  void visitIncreased(ValueLegalityState state);

  void visitHigh(ValueLegalityState visitedState);
}