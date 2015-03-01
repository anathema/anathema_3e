package net.sf.anathema.hero.charms.model.special.mechanics;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.values.Value;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyModelFetcher;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyProvider;

public class ThaumaturgyMechanic extends AbstractCharmMechanic {

	private final boolean grantsAccess;
	private final Value grantsFreeRituals;
	
	public ThaumaturgyMechanic(CharmName id, boolean access, Value freeRituals) {
		super(id);
		this.grantsAccess = access;
		this.grantsFreeRituals = freeRituals;
	}
	
	@Override
	public void initialize(Hero hero) {
		ThaumaturgyModelFetcher.fetch(hero).addThaumaturgyProvider(new CharmThaumaturgyProvider(hero));
	}
	
	private class CharmThaumaturgyProvider implements ThaumaturgyProvider {
		
		private final Hero hero;
		
		public CharmThaumaturgyProvider(Hero hero) {
			this.hero = hero;
		}

		@Override
		public boolean grantsThaumaturgy() {
			return grantsAccess && knowsCharm(hero);
		}

		@Override
		public int numberOfRitualsProvided() {
          if (grantsFreeRituals == null){
            return 0;
          }
          int valueForHero = grantsFreeRituals.getValueForHero(hero);
          int learnCount = getLearnCount(hero);
          return valueForHero * learnCount;
		}
	}
}