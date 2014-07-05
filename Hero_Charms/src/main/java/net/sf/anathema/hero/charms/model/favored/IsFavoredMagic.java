package net.sf.anathema.hero.charms.model.favored;

import net.sf.anathema.magic.data.Magic;

import java.util.ArrayList;
import java.util.List;

public class IsFavoredMagic {

  private List<CheapenedChecker> cheapenedCheckers = new ArrayList<>();

  public IsFavoredMagic() {
    this.cheapenedCheckers = new ArrayList<>();
  }

  public void add(CheapenedChecker checker) {
    cheapenedCheckers.add(checker);
  }

  public boolean isFavored(Magic magic) {
    for (CheapenedChecker checker : cheapenedCheckers) {
      if (checker.supportsMagic(magic)) {
        return checker.isCheapened(magic);
      }
    }
    return false;
  }
}
