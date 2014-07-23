package net.sf.anathema.namegenerator.domain.syllable;

import net.sf.anathema.namegenerator.domain.INameGenerator;
import net.sf.anathema.namegenerator.domain.Names;

public class SimpleSyllableNameGenerator implements INameGenerator {

  private final ICountCalculator wordCalculator;
  private final IWordFactory wordFactory;

  public SimpleSyllableNameGenerator(IWordFactory wordFactory, ICountCalculator wordCalculator) {
    this.wordFactory = wordFactory;
    this.wordCalculator = wordCalculator;
  }

  @Override
  public Names createNames(int count) {
    Names names = new Names();
    for (int nameIndex = 0; nameIndex < count; nameIndex++) {
      StringBuilder name = new StringBuilder();
      int wordCount = wordCalculator.calculateNamePartCount();
      for (int wordIndex = 0; wordIndex < wordCount; wordIndex++) {
        if (wordIndex != 0) {
          name.append(" ");
        }
        name.append(wordFactory.createWord(wordIndex));
      }
      names.add(name.toString());
    }
    return names;
  }
}