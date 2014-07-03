package net.sf.anathema.hero.charms.model.favored;

import net.sf.anathema.magic.data.Magic;

import java.util.ArrayList;
import java.util.List;

public class IsFavoredMagic {

  private List<FavoredChecker> favoredCheckers = new ArrayList<>();

  public IsFavoredMagic() {
    this.favoredCheckers = new ArrayList<>();
  }

  public void add(FavoredChecker checker) {
    favoredCheckers.add(checker);
  }

  public boolean isFavored(Magic magic) {
    for (FavoredChecker checker : favoredCheckers) {
      if (checker.supportsMagic(magic)) {
        return checker.isFavored(magic);
      }
    }
    return false;
  }
}
