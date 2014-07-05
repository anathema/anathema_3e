package net.sf.anathema.hero.traits.model.state;

public class FriendlyIncrementChecker implements IncrementChecker {

  @Override
  public boolean isValidIncrement(int increment) {
    return true;
  }
}