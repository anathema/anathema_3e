package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.main.hero.Hero;

public interface ReportContentFactory<C extends SubContent> {

  C create(ReportSession session, IGenericCharacter character, Hero hero);
}
