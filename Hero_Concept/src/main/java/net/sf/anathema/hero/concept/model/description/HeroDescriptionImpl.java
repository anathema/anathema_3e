package net.sf.anathema.hero.concept.model.description;

import net.sf.anathema.hero.elsewhere.description.HeroDescription;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.change.AnnounceChangeValueListener;
import net.sf.anathema.library.change.ChangeAnnouncer;
import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.text.ITextualDescription;
import net.sf.anathema.library.text.SimpleTextualDescription;

public class HeroDescriptionImpl implements HeroDescription, HeroModel {

  private final ITextualDescription name = new SimpleTextualDescription();
  private final ITextualDescription paraphrasis = new SimpleTextualDescription();
  private final ITextualDescription characterization = new SimpleTextualDescription();
  private final ITextualDescription physicals = new SimpleTextualDescription();
  private final ITextualDescription notes = new SimpleTextualDescription();
  private final ITextualDescription player = new SimpleTextualDescription();
  private final ITextualDescription concept = new SimpleTextualDescription();

  private final ITextualDescription eyes = new SimpleTextualDescription();
  private final ITextualDescription hair = new SimpleTextualDescription();
  private final ITextualDescription sex = new SimpleTextualDescription();
  private final ITextualDescription skin = new SimpleTextualDescription();
  private final ITextualDescription anima = new SimpleTextualDescription();

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    addOverallChangeListener(new AnnounceChangeValueListener(announcer));
  }

  @Override
  public ITextualDescription getName() {
    return name;
  }

  @Override
  public ITextualDescription getPlayer() {
    return player;
  }

  @Override
  public ITextualDescription getCharacterization() {
    return characterization;
  }

  @Override
  public ITextualDescription getPhysicalDescription() {
    return physicals;
  }

  @Override
  public ITextualDescription getEyes() {
    return eyes;
  }

  @Override
  public ITextualDescription getHair() {
    return hair;
  }

  @Override
  public ITextualDescription getSex() {
    return sex;
  }

  @Override
  public ITextualDescription getSkin() {
    return skin;
  }

  @Override
  public ITextualDescription getAnima() {
    return anima;
  }

  @Override
  public ITextualDescription getNotes() {
    return notes;
  }

  private ITextualDescription[] getAllDescriptions() {
    return new ITextualDescription[]{name, paraphrasis, characterization, physicals, eyes, hair, sex, skin, anima, notes, player, concept};
  }

  @Override
  public ITextualDescription getConcept() {
    return concept;
  }

  @Override
  public void addOverallChangeListener(ObjectChangedListener<String> listener) {
    for (ITextualDescription description : getAllDescriptions()) {
      description.addTextChangedListener(listener);
    }
  }
}