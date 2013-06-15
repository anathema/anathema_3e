package net.sf.anathema.character.platform.specialties;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesAdditionalModel;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesConfigurationView;
import net.sf.anathema.character.presenter.specialty.SpecialtiesConfigurationPresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

public class SpecialtiesInitializer implements IAdditionalInitializer {

  @Override
  public void initialize(IAdditionalModel model, Resources resources, ICharacterType type, SectionView sectionView) {
    String viewName = resources.getString("AdditionalTemplateView.TabName." + model.getTemplateId());
    ISpecialtiesConfigurationView view = sectionView.addView(viewName, ISpecialtiesConfigurationView.class, type);
    ISpecialtiesConfiguration specialtiesModel = ((ISpecialtiesAdditionalModel) model).getSpecialtiesModel();
    new SpecialtiesConfigurationPresenter(specialtiesModel, view, resources).initPresentation();
  }
}