package net.sf.anathema.hero.magic.model.charms.mechanics;

import net.sf.anathema.hero.charms.CharmHeroObjectMother;
import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.CharmsModelImpl;
import net.sf.anathema.hero.charms.model.special.mechanics.AddsHealthLevelsByTraitMechanic;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.DummyHeroEnvironment;
import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.health.model.HealthModel;
import net.sf.anathema.hero.health.model.HealthModelFetcher;
import net.sf.anathema.hero.health.model.HealthModelImpl;
import net.sf.anathema.hero.health.template.HealthTemplate;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.magic.advance.MagicPointsModel;
import net.sf.anathema.hero.magic.model.MagicModel;
import net.sf.anathema.hero.magic.template.advance.MagicPointsTemplate;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitImpl;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitRules;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitValueStrategy;
import net.sf.anathema.hero.traits.model.context.CreationTraitValueStrategy;
import net.sf.anathema.hero.traits.model.rules.TraitRulesImpl;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplateFactory;
import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.points.model.PointModelImpl;
import net.sf.anathema.points.template.PointsTemplate;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Stamina;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AddsHealthLevelsByTraitTest {
	
	private Trait resistance;
	private Trait stamina;
	private DummyHero hero;
	private HealthModel health;
	private Charm[] charms;
	private AddsHealthLevelsByTraitMechanic[] mechanics;
	
	private int MAX_OXBODY = 5;
	
	
	@Before
  public void setUp() throws Exception {
		
		TraitValueStrategy strategy = new CreationTraitValueStrategy();
    hero = new CharmHeroObjectMother().createModelContextWithEssence2(strategy);
    
    HealthTemplate healthTemplate = new HealthTemplate();
    healthTemplate.levels = Arrays.asList(HealthLevelType.ZERO,
            HealthLevelType.ONE, HealthLevelType.ONE, HealthLevelType.TWO, HealthLevelType.TWO);
    hero.addModel(new MagicModel());
    hero.addModel(new HealthModelImpl(healthTemplate));
    hero.addModel(new PointModelImpl(new PointsTemplate()));
    hero.addModel(new MagicPointsModel(new MagicPointsTemplate()));
    
    DummyHeroEnvironment dummyEnvironment = new DummyHeroEnvironment();
    DummyCharmProviderForMechanics provider = new DummyCharmProviderForMechanics();
    dummyEnvironment.dataSets.add(provider);
    createMechanics("Solar.OxBody", MAX_OXBODY, provider);
    
    CharmsModelImpl charms = new CharmsModelImpl(new CharmsTemplate());
    hero.addModel(charms);
    charms.initialize(dummyEnvironment, hero);
    
    stamina = createTrait(hero, Stamina);
    resistance = createTrait(hero, AbilityType.Resistance);
    health = HealthModelFetcher.fetch(hero);
    TraitModel traitModel = TraitModelFetcher.fetch(hero);
    traitModel.addTraits(stamina, resistance);
    
    stamina.setCurrentValue(1);
  }
	
	@Test
	public void verifyWithoutOxBody() throws Exception {
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.ZERO), is(1));
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.ONE), is(2));
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.TWO), is(2));
	}
	
	@Test
	public void verifyWithStaminaOneAndOneOxBody() throws Exception {
		learnOxBodies(1);
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.ZERO), is(1));
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.ONE), is(3));
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.TWO), is(2));
	}
	
	@Test
	public void verifyWithStaminaFiveAndOneOxBody() throws Exception {
		learnOxBodies(1);
		stamina.setCurrentValue(5);
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.ZERO), is(2));
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.ONE), is(4));
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.TWO), is(2));
	}
	
	@Test
	public void verifyWithStaminaOneAndTwoOxBody() throws Exception {
		learnOxBodies(2);
		stamina.setCurrentValue(1);
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.ZERO), is(1));
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.ONE), is(4));
		assertThat(health.getHealthLevelTypeCount(HealthLevelType.TWO), is(2));
	}
	
	private void learnOxBodies(int count) {

    resistance.setCurrentValue(count);
    int i;
    for (i = 0; i < count; i++) {
    	CharmsModelFetcher.fetch(hero).getLearningModel().learnCharm(charms[i], false);	
    }
    for (; i < MAX_OXBODY; i++) {
    	CharmsModelFetcher.fetch(hero).getLearningModel().forgetCharm(charms[i], false);
    }
    
  }
	
	private TraitImpl createTrait(Hero hero, TraitType type) {
    TraitTemplate traitTemplate = TraitTemplateFactory.createEssenceLimitedTemplate(0);
    TraitRules traitRules = new TraitRulesImpl(type, traitTemplate, hero);
    return new TraitImpl(hero, traitRules);
  }
	
	private void createMechanics(String charmName, int numberOfMechanics, DummyCharmProviderForMechanics provider) {
		mechanics = new AddsHealthLevelsByTraitMechanic[numberOfMechanics];
		charms = new Charm[numberOfMechanics];
		Map<Integer, HealthLevelType[]> healthLevels = new HashMap<>();
		healthLevels.put(1, new HealthLevelType[] { HealthLevelType.ONE });
		healthLevels.put(2, new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.TWO });
		healthLevels.put(3, new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.TWO, HealthLevelType.TWO });
		healthLevels.put(4, new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.ONE, HealthLevelType.TWO });
		healthLevels.put(5, new HealthLevelType[] { HealthLevelType.ZERO, HealthLevelType.ONE, HealthLevelType.ONE });
		for (int i = 1; i <= numberOfMechanics; i++) {
			CharmName name = new CharmName(charmName + (i > 1 ? ".x" + i : ""));
			mechanics[i - 1] = new AddsHealthLevelsByTraitMechanic(name,
					Stamina,
					healthLevels);
			charms[i - 1] = new DummyCharm(name.text);
			provider.addCharm(charms[i - 1], mechanics[i - 1]);
		}
	}
	
	
}
