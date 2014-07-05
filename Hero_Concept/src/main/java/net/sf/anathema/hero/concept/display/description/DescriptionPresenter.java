package net.sf.anathema.hero.concept.display.description;

import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.concept.model.description.HeroDescription;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.model.IntegerModel;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.text.ITextualDescription;
import net.sf.anathema.library.text.TextualPresentation;
import net.sf.anathema.library.view.ConfigurableCharacterView;
import net.sf.anathema.library.view.IntegerView;
import net.sf.anathema.library.view.MultiComponentLine;

public class DescriptionPresenter {

  private final HeroDescription description;
  private final HeroConcept heroConcept;
  private final ConfigurableCharacterView descriptionView;
  private final boolean hasAnima;
  private HeroEnvironment environment;

  public DescriptionPresenter(DescriptionDetails descriptionDetails, HeroEnvironment environment, ConfigurableCharacterView descriptionView) {
    this.environment = environment;
    this.description = descriptionDetails.getDescription();
    this.heroConcept = descriptionDetails.getHeroConcept();
    this.hasAnima = descriptionDetails.isHasAnima();
    this.descriptionView = descriptionView;
  }

  public void initPresentation() {
    TextualPresentation presentation = new TextualPresentation();
    initNameLineView(presentation);
    initLineView("CharacterDescription.Label.Player", description.getPlayer(), presentation);
    initLineView("Label.Concept", description.getConcept(), presentation);
    if (hasAnima) {
      initLineView("CharacterDescription.Label.Anima", description.getAnima(), presentation);
    }
    initAreaView("CharacterDescription.Label.Characterization", description.getCharacterization(), presentation);
    initAreaView("CharacterDescription.Label.PhysicalDescription", description.getPhysicalDescription(), presentation);
    initMinorTraits(presentation);
    initAreaView("CharacterDescription.Label.Notes", description.getNotes(), presentation);
  }

  private void initNameLineView(TextualPresentation presentation) {
    initLineView("CharacterDescription.Label.Name", description.getName(), presentation);
    ObjectFactory objectFactory = environment.getObjectFactory();
    Resources resources = environment.getResources();
    for (NameEditAction action : objectFactory.<NameEditAction>instantiateOrdered(RegisteredNameEditAction.class,
      resources)) {
      addEditTool(action);
    }
  }

  private void addEditTool(NameEditAction action) {
    Tool tool = descriptionView.addEditAction();
    action.configure(tool, description.getName());
  }

  private void initMinorTraits(TextualPresentation presentation) {
    MultiComponentLine basicLooks = descriptionView.addMultiComponentLine();
    addField(basicLooks, "CharacterDescription.Label.Hair", description.getHair(), presentation);
    addField(basicLooks, "CharacterDescription.Label.Skin", description.getSkin(), presentation);
    addField(basicLooks, "CharacterDescription.Label.Eyes", description.getEyes(), presentation);
    MultiComponentLine sexAndAge = descriptionView.addMultiComponentLine();
    addField(sexAndAge, "CharacterDescription.Label.Sex", description.getSex(), presentation);
    addInteger(sexAndAge, "Label.Age", heroConcept.getAge());
  }

  private void addInteger(MultiComponentLine componentLine, String label, final IntegerModel integerDescription) {
    String title = environment.getResources().getString(label);
    IntegerView view = componentLine.addIntegerView(title, integerDescription);
    view.addChangeListener(integerDescription::setValue);
  }

  private void addField(MultiComponentLine componentLine, String label, ITextualDescription description, TextualPresentation presentation) {
    String labelText = environment.getResources().getString(label);
    ITextView textView = componentLine.addFieldsView(labelText);
    presentation.initView(textView, description);
  }

  private void initLineView(String labelResourceKey, ITextualDescription textualDescription, TextualPresentation presentation) {
    ITextView textView = descriptionView.addLineView(environment.getResources().getString(labelResourceKey));
    presentation.initView(textView, textualDescription);
  }

  private void initAreaView(String labelResourceKey, ITextualDescription textualDescription, TextualPresentation presentation) {
    ITextView textView = descriptionView.addAreaView(environment.getResources().getString(labelResourceKey));
    presentation.initView(textView, textualDescription);
  }
}