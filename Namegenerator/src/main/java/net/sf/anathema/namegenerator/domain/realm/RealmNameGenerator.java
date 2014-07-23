package net.sf.anathema.namegenerator.domain.realm;

import net.sf.anathema.namegenerator.domain.INameGenerator;
import net.sf.anathema.namegenerator.domain.Names;
import net.sf.anathema.namegenerator.domain.syllable.SimpleSyllableNameGenerator;

public class RealmNameGenerator implements INameGenerator {

  private final INameGenerator realmNameGenerator;

  public RealmNameGenerator() {
    this(20);
  }

  public RealmNameGenerator(int familyNamePercent) {
    this.realmNameGenerator = new SimpleSyllableNameGenerator(
        new RealmWordFactory(familyNamePercent),
        new RealmWordCalculator());
  }

  @Override
  public Names createNames(int count) {
    Names names = new Names();
    for (int index = 0; index < count; index++) {
      String newName = realmNameGenerator.createNames(1).getFirst();
      names.add(newName);
    }
    return names;
  }
}