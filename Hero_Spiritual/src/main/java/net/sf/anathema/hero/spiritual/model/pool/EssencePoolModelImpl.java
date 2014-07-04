package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.spiritual.template.EssencePoolTemplate;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.library.change.ChangeAnnouncer;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.List;

public class EssencePoolModelImpl implements EssencePoolModel, HeroModel {

  private final AggregatedOverdrivePool overdrivePool = new AggregatedOverdrivePool();
  private EssencePoolStrategy poolStrategy = null;
  private List<IEssencePoolModifier> essencePoolModifiers = new ArrayList<>();
  private EssencePoolTemplate template;
  private Hero hero;

  public EssencePoolModelImpl(EssencePoolTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
    if (!isEssenceUser()) {
      return;
    }
    TraitMap traitMap = TraitModelFetcher.fetch(hero);
    EssencePoolConfiguration essencePoolConfiguration = new EssencePoolConfiguration(template);
    poolStrategy = new EssencePoolStrategyImpl(hero, essencePoolConfiguration, traitMap);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  @Override
  public String getPersonalPool() {
    if (!isEssenceUser()) {
      return null;
    }
    if (!hasAdditionalPools()) {
      return String.valueOf(poolStrategy.getStandardPersonalPool());
    }
    return poolStrategy.getStandardPersonalPool() + " (" + poolStrategy.getExtendedPersonalPool() + ")";
  }

  @Override
  public int getPersonalPoolValue() {
    return poolStrategy.getFullPersonalPool();
  }

  @Override
  public String getPeripheralPool() {
    if (!isEssenceUser()) {
      return null;
    }
    if (!hasAdditionalPools()) {
      return String.valueOf(poolStrategy.getStandardPeripheralPool());
    }
    return poolStrategy.getStandardPeripheralPool() + " (" + poolStrategy.getExtendedPeripheralPool() + ")";
  }

  @Override
  public int getPeripheralPoolValue() {
    return poolStrategy.getFullPeripheralPool();
  }

  @Override
  public String getAttunedPool() {
    return "" + poolStrategy.getAttunementExpenditures();
  }

  @Override
  public int getAttunedPoolValue() {
    return poolStrategy.getAttunementExpenditures();
  }

  private boolean hasAdditionalPools() {
    return false;
  }

  @Override
  public boolean isEssenceUser() {
    return hero.getSplat().getTemplateType().getCharacterType().isEssenceUser();
  }

  @Override
  public boolean hasPeripheralPool() {
    return isEssenceUser() && (poolStrategy.getExtendedPeripheralPool() != 0 || poolStrategy.getUnmodifiedPeripheralPool() != 0);
  }

  @Override
  public void addPoolChangeListener(ChangeListener listener) {
    poolStrategy.addPoolChangeListener(listener);
  }

  @Override
  public Iterable<IEssencePoolModifier> getEssencePoolModifiers() {
    return essencePoolModifiers;
  }

  @Override
  public void addEssencePoolModifier(IEssencePoolModifier modifier) {
    essencePoolModifiers.add(modifier);
  }
}