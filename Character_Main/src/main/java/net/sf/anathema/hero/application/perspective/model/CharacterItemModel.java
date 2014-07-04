package net.sf.anathema.hero.application.perspective.model;

import net.sf.anathema.hero.application.item.Item;
import net.sf.anathema.hero.application.perspective.DescriptiveFeatures;
import net.sf.anathema.hero.application.perspective.LoadedDescriptiveFeatures;
import net.sf.anathema.hero.elsewhere.concept.HeroConceptFetcher;
import net.sf.anathema.hero.elsewhere.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.event.ChangeListener;
import org.jmock.example.announcer.Announcer;

public class CharacterItemModel {

  private DescriptiveFeatures descriptiveFeatures;
  private Item item;
  private final Announcer<ChangeListener> featuresChangeAnnouncer = Announcer.to(ChangeListener.class);

  public CharacterItemModel(DescriptiveFeatures descriptiveFeatures) {
    this.descriptiveFeatures = descriptiveFeatures;
  }

  public CharacterItemModel(CharacterIdentifier identifier, Item item) {
    setItem(identifier, item);
  }

  public DescriptiveFeatures getDescriptiveFeatures() {
    return descriptiveFeatures;
  }

  public void setItem(Item item) {
    CharacterIdentifier identifier = descriptiveFeatures.getIdentifier();
    setItem(identifier, item);
  }

  private void setItem(CharacterIdentifier identifier, Item item) {
    this.item = item;
    this.descriptiveFeatures = new LoadedDescriptiveFeatures(identifier, item);
    Hero hero = (Hero) item.getItemData();
    HeroConceptFetcher.fetch(hero).getCaste().addChangeListener(new AnnouncingChangeListener());
    HeroDescriptionFetcher.fetch(hero).getName().addTextChangedListener(newValue -> featuresChangeAnnouncer.announce().changeOccurred());
    item.getItemData().getChangeManagement().addDirtyListener(new AnnouncingChangeListener());
  }

  public boolean isLoaded() {
    return item != null;
  }

  public Item getItem() {
    return item;
  }

  public void whenFeaturesChange(ChangeListener listener) {
    featuresChangeAnnouncer.addListener(listener);
  }

  private class AnnouncingChangeListener implements ChangeListener {
    @Override
    public void changeOccurred() {
      featuresChangeAnnouncer.announce().changeOccurred();
    }
  }
}
