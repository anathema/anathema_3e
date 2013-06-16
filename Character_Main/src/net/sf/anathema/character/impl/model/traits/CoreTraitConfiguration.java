package net.sf.anathema.character.impl.model.traits;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.traits.creation.DefaultTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.impl.model.traits.listening.WillpowerListening;
import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.main.abilities.DefaultAbilityConfiguration;
import net.sf.anathema.character.main.traits.model.TraitModel;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class CoreTraitConfiguration extends AbstractTraitCollection implements ICoreTraitConfiguration, TraitModel {

  private final FavorableTraitFactory favorableTraitFactory;
  private final ITraitTemplateCollection traitTemplateCollection;
  private final DefaultAbilityConfiguration abilityConfiguration;

  public CoreTraitConfiguration(ICharacterTemplate template, ICharacterModelContext modelContext) {
    this.abilityConfiguration = new DefaultAbilityConfiguration(template, modelContext, this, this);
    traitTemplateCollection = template.getTraitTemplateCollection();
    this.favorableTraitFactory = createFactory(template, modelContext);
    addEssence(modelContext.getTraitContext(), traitTemplateCollection, template.getAdditionalRules().getAdditionalTraitRules());
    addVirtues(modelContext.getTraitContext(), traitTemplateCollection, template.getAdditionalRules().getAdditionalTraitRules());
    addWillpower(modelContext.getTraitContext(), traitTemplateCollection, template.getAdditionalRules().getAdditionalTraitRules());
    Trait willpower = TraitCollectionUtilities.getWillpower(this);
    Trait[] virtues = TraitCollectionUtilities.getVirtues(this);
    if (template.getAdditionalRules().getAdditionalTraitRules().isWillpowerVirtueBased()) {
      new WillpowerListening().initListening(willpower, virtues);
    } else {
      willpower.setModifiedCreationRange(5, 10);
    }
    getTrait(OtherTraitType.Essence).addCurrentValueListener(new EssenceLimitationListener(new AllTraits(), modelContext));
  }

  private FavorableTraitFactory createFactory(ICharacterTemplate template, ICharacterModelContext modelContext) {
    return new FavorableTraitFactory(modelContext.getTraitContext(), template.getAdditionalRules().getAdditionalTraitRules(),
            modelContext.getBasicCharacterContext(), modelContext.getCharacterListening());
  }

  private void addEssence(ITraitContext traitContext, ITraitTemplateCollection traitTemplateCollection, IAdditionalTraitRules additionalTraitRules) {
    TypedTraitTemplateFactory templateFactory = new EssenceTemplateFactory(traitTemplateCollection.getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, additionalTraitRules, templateFactory);
    addTraits(traitFactory.createTrait(OtherTraitType.Essence));
  }

  private void addVirtues(ITraitContext traitContext, ITraitTemplateCollection traitTemplateCollection, IAdditionalTraitRules additionalTraitRules) {
    TypedTraitTemplateFactory templateFactory = new VirtueTemplateFactory(traitTemplateCollection.getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, additionalTraitRules, templateFactory);
    addTraits(traitFactory.createTraits(VirtueType.values()));
  }

  private void addWillpower(ITraitContext traitContext, ITraitTemplateCollection traitTemplateCollection,
                            IAdditionalTraitRules additionalTraitRules) {
    TypedTraitTemplateFactory templateFactory = new WillpowerTemplateFactory(traitTemplateCollection.getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, additionalTraitRules, templateFactory);
    addTraits(traitFactory.createTrait(OtherTraitType.Willpower));
  }

  @Override
  public void addFavorableTraits(IIdentifiedCasteTraitTypeGroup[] traitGroups, IIncrementChecker incrementChecker,
                                 TypedTraitTemplateFactory factory) {
    for (IIdentifiedCasteTraitTypeGroup traitGroup : traitGroups) {
      Trait[] traits = favorableTraitFactory.createTraits(traitGroup, incrementChecker, factory);
      addTraits(traits);
    }
  }

  @Override
  public Trait getTrait(ITraitType traitType) {
    if (contains(traitType)) {
      return super.getTrait(traitType);
    }
    throw new UnsupportedOperationException("Unsupported trait type " + traitType);
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    return abilityConfiguration.getAbilityTypeGroups();
  }

  @Override
  public ISpecialtiesConfiguration getSpecialtyConfiguration() {
    return abilityConfiguration.getSpecialtyConfiguration();
  }

  private class AllTraits implements TraitProvider {
    @Override
    public Iterator<Trait> iterator() {
      return asList(getAllTraits()).iterator();
    }
  }

  public class GenericTraitTemplateFactory implements TypedTraitTemplateFactory {
    @Override
    public ITraitTemplate create(ITraitType type) {
      return traitTemplateCollection.getTraitTemplate(type);
    }
  }
}