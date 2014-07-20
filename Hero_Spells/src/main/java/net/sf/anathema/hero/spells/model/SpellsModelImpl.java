package net.sf.anathema.hero.spells.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.advance.MagicPointsModelFetcher;
import net.sf.anathema.hero.charms.advance.experience.MagicExperienceData;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceChange;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.change.UnspecifiedChangeListener;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spells.advance.SpellExperienceCostCalculator;
import net.sf.anathema.hero.spells.advance.SpellExperienceModel;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;
import net.sf.anathema.hero.spells.parser.SpellCache;
import net.sf.anathema.hero.spells.sheet.content.PrintSpellsProvider;
import net.sf.anathema.hero.spells.template.SpellsTemplate;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.points.model.PointModelFetcher;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpellsModelImpl implements SpellsModel {

  private final ProxySpellLearnStrategy strategy = new ProxySpellLearnStrategy(new CreationSpellLearnStrategy());
  private final List<Spell> creationLearnedList = new ArrayList<>();
  private final List<Spell> experiencedLearnedList = new ArrayList<>();
  private final Announcer<ChangeListener> changeControl = Announcer.to(ChangeListener.class);
  private final Multimap<CircleType, Spell> spellsByCircle = ArrayListMultimap.create();
  private CharmsModel charms;
  private ExperienceModel experience;
  private SpellsTemplate template;

  public SpellsModelImpl(SpellsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.charms = CharmsModelFetcher.fetch(hero);
    this.experience = ExperienceModelFetcher.fetch(hero);
    charms.addCheapenedChecker(new IsFavoredSpell(hero));
    initializeSpellsByCircle(environment);
    initializeCharmsModel(hero);
    initializeExperience(hero);
  }

  private void initializeExperience(Hero hero) {
    MagicExperienceData experienceCost = MagicPointsModelFetcher.fetch(hero).getExperienceCost();
    SpellExperienceCostCalculator calculator = new SpellExperienceCostCalculator(experienceCost);
    PointModelFetcher.fetch(hero).addToExperienceOverview(new SpellExperienceModel(hero, calculator));
  }

  private void initializeSpellsByCircle(HeroEnvironment environment) {
    for (Spell spell : environment.getDataSet(SpellCache.class).getSpells()) {
      spellsByCircle.put(spell.getCircleType(), spell);
    }
  }

  private void initializeCharmsModel(Hero hero) {
    CharmsModel charmsModel = CharmsModelFetcher.fetch(hero);
    if (charmsModel == null) {
      return;
    }
    charmsModel.addPrintProvider(new PrintSpellsProvider(hero));
    charmsModel.addLearnProvider(new SpellsLearner(this));
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    announcer.addListener(flavor -> {
      if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
        boolean experienced = experience.isExperienced();
        updateLearnStrategies(experienced);
      }
    });
    addChangeListener(new UnspecifiedChangeListener(announcer));
  }

  private void updateLearnStrategies(boolean experienced) {
    if (experienced) {
      strategy.setStrategy(new ExperiencedSpellLearnStrategy());
    } else {
      strategy.setStrategy(new CreationSpellLearnStrategy());
    }
  }

  @Override
  public void removeSpells(Spells removedSpells) {
    strategy.removeSpells(this, removedSpells);
  }

  @Override
  public void removeSpells(Spells removedSpells, boolean experienced) {
    for (Spell spell : removedSpells) {
      if (experienced) {
        experiencedLearnedList.remove(spell);
      } else {
        creationLearnedList.remove(spell);
      }
    }
    fireChangeEvent();
  }

  @Override
  public void addSpells(Spells addedSpells) {
    Spells allowedSpells = new Spells();
    for (Spell spell : addedSpells) {
      if (isSpellAllowed(spell)) {
        allowedSpells.add(spell);
      }
    }
    strategy.addSpells(this, allowedSpells);
  }

  @Override
  public void addSpells(Spells addedSpells, boolean experienced) {
    for (Spell spell : addedSpells) {
      if (isSpellAllowed(spell, experienced)) {
        if (experienced) {
          experiencedLearnedList.add(spell);
        } else {
          creationLearnedList.add(spell);
        }
      } else {
        throw new IllegalArgumentException("Cannot learn Spell: " + spell);
      }
    }
    fireChangeEvent();
  }

  private void fireChangeEvent() {
    changeControl.announce().changeOccurred();
  }

  @Override
  public boolean isSpellAllowed(Spell spell) {
    return strategy.isSpellAllowed(this, spell);
  }

  @SuppressWarnings("SimplifiableIfStatement")
  @Override
  public boolean isSpellAllowed(Spell spell, boolean experienced) {
    boolean alreadyKnowsSpell = creationLearnedList.contains(spell) || (experienced && experiencedLearnedList.contains(
            spell));
    if (alreadyKnowsSpell) {
      return false;
    }
    String initiationCharm = getInitiation(spell.getCircleType());
    return charms.isLearned(new CharmName(initiationCharm));
  }

  @Override
  public Spells getLearnedSpells() {
    return strategy.getLearnedSpells(this);
  }

  @Override
  public Spells getLearnedSpells(boolean experienced) {
    Spells spells = new Spells();
    spells.addAll(creationLearnedList);
    if (experienced) {
      spells.addAll(experiencedLearnedList);
    }
    return spells;
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    changeControl.addListener(listener);
  }

  private Spells getSpellsByCircle(CircleType circle) {
    return Spells.copyOf(spellsByCircle.get(circle));
  }

  @Override
  public Spell getSpellById(String id) {
    for (Spell spell : getAllSpells()) {
      if (spell.getName().text.equals(id)) {
        return spell;
      }
    }
    throw new IllegalArgumentException("No Spell for id: " + id);
  }

  private Iterable<Spell> getAllSpells() {
    return new ArrayList<>(spellsByCircle.values());
  }

  @Override
  public boolean isLearnedOnCreation(Spell spell) {
    return creationLearnedList.contains(spell);
  }

  @Override
  public boolean isLearnedOnCreationOrExperience(Spell spell) {
    return experiencedLearnedList.contains(spell) || isLearnedOnCreation(spell);
  }

  @Override
  public Spells getAvailableSpellsInCircle(CircleType circle) {
    Spells showSpells = new Spells();
    showSpells.adopt(getSpellsByCircle(circle));
    showSpells.removeAll(getLearnedSpells());
    return showSpells;
  }

  @Override
  public Spells getLearnedSpellsInCircles(Collection<CircleType> eligibleCircles) {
    Spells spells = new Spells();
    for (Spell spell : getLearnedSpells()) {
      if (eligibleCircles.contains(spell.getCircleType())) {
        spells.add(spell);
      }
    }
    return spells;
  }

  @Override
  public boolean canLearnSorcery() {
    return !template.sorcery.isEmpty();
  }

  @Override
  public boolean canLearnNecromancy() {
    return !template.necromancy.isEmpty();
  }

  @Override
  public Collection<CircleType> getNecromancyCircles() {
    return template.necromancy.keySet();
  }

  @Override
  public Collection<CircleType> getSorceryCircles() {
    return template.sorcery.keySet();
  }

  @Override
  public TraitType getFavoringTraitType() {
    return new TraitTypeFinder().getTrait(template.favoringTrait);
  }

  private String getInitiation(CircleType type) {
    String charmId = template.necromancy.get(type);
    if (charmId == null) {
      charmId = template.sorcery.get(type);
    }
    return charmId;
  }
}