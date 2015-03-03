package net.sf.anathema.hero.thaumaturgy;

import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.DummyHeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.experience.model.ExperienceModelImpl;
import net.sf.anathema.hero.merits.model.MeritsModelImpl;
import net.sf.anathema.hero.thaumaturgy.advance.ThaumaturgyExperienceModel;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyModel;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyModelImpl;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyProvider;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyRitual;
import net.sf.anathema.hero.traits.dummy.DummyTraitModel;
import net.sf.anathema.library.model.OptionalEntryReference;
import net.sf.anathema.points.model.PointModelImpl;
import net.sf.anathema.points.model.PointsModel;
import net.sf.anathema.points.template.PointsTemplate;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ThaumaturgyTests {
	
	DummyHero hero = new DummyHero();
	ThaumaturgyModel model = new ThaumaturgyModelImpl();
	PointsModel points = new PointModelImpl(new PointsTemplate());
	ExperienceModel xp;
	
	
	@Before
  public void setUp() throws Exception {
    DummyTraitModel traits = new DummyTraitModel();
    hero.addModel(traits);
    hero.addModel(model);
    hero.addModel(points);
    hero.addModel(new MeritsModelImpl());
    
    DummyHeroEnvironment dummyEnvironment = new DummyHeroEnvironment();
    DummyRitualProvider provider = new DummyRitualProvider();
    dummyEnvironment.dataSets.add(provider);
    
    xp = new ExperienceModelImpl();
    xp.setExperienced(true);
    hero.addModel(xp);
    
    model.initialize(dummyEnvironment, hero);
  }
	
	@Test
	public void verifyNoThaumaturgyIfNotGranted() {
		model.selectFirstEntryOption();
		assertThat(model.isEntryAllowed(), is(false));
	}
	
	@Test
	public void verifyThaumaturgyLearnableIfGranted() {
		model.addThaumaturgyProvider(getAllowsThaumaturgyProvider());
		model.selectFirstEntryOption();
		assertThat(model.isEntryAllowed(), is(true));
	}
	
	@Test
	public void verifyThaumaturgyBasicCost() {
		model.addThaumaturgyProvider(getAllowsThaumaturgyProvider());
		model.setSelectedEntryOption(getOption("Second Bread"));
		model.commitSelection();
		assertThat(points.getExperiencePointManagement().getTotalCosts(),
				is(ThaumaturgyExperienceModel.BASIC_RITUAL_COST));
	}
	
	@Test
	public void verifyThaumaturgyAdvancedCost() {
		model.addThaumaturgyProvider(getAllowsThaumaturgyProvider());
		model.setSelectedEntryOption(getOption("Speak To Ozashun"));
		model.commitSelection();
		assertThat(points.getExperiencePointManagement().getTotalCosts(),
				is(ThaumaturgyExperienceModel.ADVANCED_RITUAL_COST));
	}
	
	@Test
	public void verifyThaumaturgyFreeRitualDiscountCost() {
		model.addThaumaturgyProvider(getFreeRitualProvider());
		model.setSelectedEntryOption(getOption("Second Bread"));
		model.commitSelection();
		model.setSelectedEntryOption(getOption("Read The Tea Leaves"));
		model.commitSelection();
		model.setSelectedEntryOption(getOption("Speak To Ozashun"));
		model.commitSelection();
		assertThat(points.getExperiencePointManagement().getTotalCosts(),
				is(2 * ThaumaturgyExperienceModel.BASIC_RITUAL_COST));
	}
	
	private ThaumaturgyRitual getOption(String name) {
		return model.findOptionByReference(new OptionalEntryReference(name));
	}
	
	private ThaumaturgyProvider getAllowsThaumaturgyProvider() {
		return new TestThaumaturgyProvider(true, 0);
	}
	
	private ThaumaturgyProvider getFreeRitualProvider() {
		return new TestThaumaturgyProvider(true, 1);
	}
	
	private class TestThaumaturgyProvider implements ThaumaturgyProvider {
		private final boolean grantsThaumaturgy;
		private final int grantsFreeRituals;
		
		public TestThaumaturgyProvider(boolean grants, int freeRituals) {
			this.grantsThaumaturgy = grants;
			this.grantsFreeRituals = freeRituals;
		}
		
		@Override
		public boolean grantsThaumaturgy() {
			return grantsThaumaturgy;
		}
		@Override
		public int numberOfRitualsProvided() {
			return grantsFreeRituals;
		}
	}
}
