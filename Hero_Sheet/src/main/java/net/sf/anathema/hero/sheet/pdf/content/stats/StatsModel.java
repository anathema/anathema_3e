package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.StatsModifierFactory;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface StatsModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Stats");

  void addModifierFactory(StatsModifierFactory factory);

  Iterable<StatsModifierFactory> getModifierFactories();
}
